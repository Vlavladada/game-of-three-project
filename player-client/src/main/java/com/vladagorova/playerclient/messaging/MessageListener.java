package com.vladagorova.playerclient.messaging;

import com.vladagorova.playerclient.api.client.PlayService;
import com.vladagorova.playerclient.presenter.PlayerDialogPresenter;
import com.vladagorova.playerclient.messaging.entity.GameOverMessage;
import com.vladagorova.playerclient.messaging.entity.MoveWasMadeMessage;
import com.vladagorova.playerclient.player.Player;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MessageListener {

    private final PlayService playService;
    private final PlayerDialogPresenter dialogPresenter;

    public MessageListener(PlayService playService, PlayerDialogPresenter dialogPresenter) {
        this.playService = playService;
        this.dialogPresenter = dialogPresenter;
    }

    @RabbitListener(queues = "${spring.application.name}.event.in")
    public void moveWasMadeMessageListener(MoveWasMadeMessage message) {
        dialogPresenter.presentOpponentMoveInfoMessage(message.getPlayerName(), message.getMove(), message.getResultingNumber());
        dialogPresenter.presentMoveRequestMessage();
        playService.makeMove(message.getResultingNumber());
    }

    @RabbitListener(queues = "${spring.application.name}.game.over")
    public void gameOverMessageListener(GameOverMessage message) {
        switch (message.getResult()) {
            case GameOverMessage.WIN_RESULT:
                dialogPresenter.presentGameWonMessage();
                break;
            case GameOverMessage.LOSE_RESULT:
                dialogPresenter.presentGameLostMessage();
                break;
        }
        playService.requestNewPlayRound();
    }

}