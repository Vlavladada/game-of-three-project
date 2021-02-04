package com.vladagorova.playerclient.api.client;

import com.vladagorova.playerclient.api.client.entity.StartGameRequest;
import com.vladagorova.playerclient.messaging.entity.MoveWasMadeMessage;
import com.vladagorova.playerclient.player.Player;
import com.vladagorova.playerclient.presenter.PlayerDialogPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class PlayService {

    @Value("${game.host}")
    private String BASE_URL;
    private final String START_GAME_REQUEST = "start";
    private final String MAKE_MOVE_REQUEST = "move";
    private final String HEALTH_CHECK = "health";


    private final RestTemplate restTemplate;
    private final PlayerDialogPresenter dialogPresenter;
    private final Player player;

    private final Logger logger = LoggerFactory.getLogger(PlayService.class);

    public PlayService(RestTemplate restTemplate, PlayerDialogPresenter dialogPresenter, Player player) {
        this.restTemplate = restTemplate;
        this.dialogPresenter = dialogPresenter;
        this.player = player;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startGame() {
        preventNoGameOfThreeServiceAvailableErrors();
        if (!gameIsAlreadyStarted()) {
            String uri = BASE_URL + START_GAME_REQUEST;
            StartGameRequest request = new StartGameRequest();
            request.setPlayerName(player.getName());
            dialogPresenter.presentInitialNumberRequestMessage();
            request.setInitialNumber(player.setInitialNumber());
            ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
            dialogPresenter.presentGameStartRequestResult(response.getBody());
        }
    }

    private boolean gameIsAlreadyStarted() {
        final String uri = BASE_URL + START_GAME_REQUEST;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(uri, Boolean.class);
        boolean gameStarted = response.getBody();
        if (gameStarted) {
            dialogPresenter.presentGameAlreadyStartedMessage();
        }
        return gameStarted;
    }

    public void makeMove(Integer resultingNumber) {
        String uri = BASE_URL + MAKE_MOVE_REQUEST;
        int move = player.getMove(resultingNumber);
        MoveWasMadeMessage message = new MoveWasMadeMessage();
        message.setMove(move);
        message.setPlayerName(player.getName());
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(uri, message, String.class);
            dialogPresenter.presentMakeMoveRequestResult(response.getBody());
        } catch (HttpClientErrorException.BadRequest e) {
            dialogPresenter.presentMakeMoveRequestException();
            dialogPresenter.presentMoveRequestMessage();
            makeMove(resultingNumber);
        }
    }

    public void requestNewPlayRound() {
        dialogPresenter.presentRequestNewPlayRoundMessage();
        startGame();
    }

    private void preventNoGameOfThreeServiceAvailableErrors() {
        int statusCode = 0;
        while (statusCode != 200) {
            try {
                statusCode = restTemplate.getForEntity(BASE_URL + HEALTH_CHECK, String.class).getStatusCodeValue();
            } catch (ResourceAccessException e) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                dialogPresenter.presentNoGameOfThreeServiceAvailableMessage();
            }
        }
    }

}