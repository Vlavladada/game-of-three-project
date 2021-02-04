package com.vladagorova.gameofthree.gamelogic;

import com.vladagorova.gameofthree.messaging.entity.MoveWasMadeMessage;
import com.vladagorova.gameofthree.messaging.entity.StartGameMessage;
import com.vladagorova.gameofthree.move.MoveNotValidException;
import com.vladagorova.gameofthree.player.PlayerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/game-of-three")
public class GameLogicController {

    private final Logger logger = LoggerFactory.getLogger(GameLogicController.class);

    private final GameLogicService gameLogicService;

    public GameLogicController(GameLogicService gameLogicService) {
        this.gameLogicService = gameLogicService;
    }

    @GetMapping("/health")
    public void healthCheck() {

    }

    @GetMapping("/start")
    public boolean gameStarted(HttpServletResponse response) {
        return gameLogicService.gameStarted();
    }

    @PostMapping("/start")
    public String startNewGame(@RequestHeader("application-name") String nameOfRequestInitiatorApp,
                               @RequestBody StartGameMessage startGameMessage) throws PlayerNotFoundException, MoveNotValidException {
        return gameLogicService.startNewGame(nameOfRequestInitiatorApp, startGameMessage);
    }

    @PostMapping("/move")
    public String move(@RequestHeader("application-name") String nameOfRequestInitiatorApp, @RequestBody MoveWasMadeMessage message) throws PlayerNotFoundException, MoveNotValidException {
        return gameLogicService.move(nameOfRequestInitiatorApp, message);
    }

    @ExceptionHandler(MoveNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Move not allowed.")
    @SuppressWarnings("squid:S1186")
    public void MoveNotValidExceptionHandler() {

    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Player not found.")
    @SuppressWarnings("squid:S1186")
    public void PlayerNotFoundExceptionHandler() {

    }

}


