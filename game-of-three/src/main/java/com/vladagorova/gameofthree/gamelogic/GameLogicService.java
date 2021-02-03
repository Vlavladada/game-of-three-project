package com.vladagorova.gameofthree.gamelogic;

import com.vladagorova.gameofthree.messaging.MessagingService;
import com.vladagorova.gameofthree.messaging.entity.GameOverMessage;
import com.vladagorova.gameofthree.messaging.entity.MoveWasMadeMessage;
import com.vladagorova.gameofthree.messaging.entity.StartGameMessage;
import com.vladagorova.gameofthree.move.Move;
import com.vladagorova.gameofthree.move.MoveGameOfThree;
import com.vladagorova.gameofthree.move.MoveNotValidException;
import com.vladagorova.gameofthree.player.Player;
import com.vladagorova.gameofthree.player.PlayerNotFoundException;
import com.vladagorova.gameofthree.player.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class GameLogicService {
    private final MessagingService messagingService;
    private final PlayerService playerService;
    private GameLogic gameLogic;

    private boolean gameReserved = false;

    public GameLogicService(MessagingService messagingService, PlayerService playerService) {
        this.messagingService = messagingService;
        this.playerService = playerService;
    }

    public boolean gameStarted() {
        if (!gameReserved) {
            gameReserved = true;
            return false;
        } else {
            return true;
        }
    }

    public String startNewGame(String nameOfRequestInitiatorApp, StartGameMessage startGameMessage) throws PlayerNotFoundException {
        if (gameLogic != null) {
            return ("Sorry, game has already started. Other player was faster, than you ;)");
        }
        gameLogic = new GameOfThreeLogic(startGameMessage.getInitialNumber());
        MoveWasMadeMessage message = mapStartMessageToMoveWasMadeMessage(startGameMessage);
        playerService.setPlayersByFirstPlayerApplicationName(nameOfRequestInitiatorApp);

        String nameOfQueueToSendTo = playerService
                .getOpponentPlayerByPlayerApplicationName(nameOfRequestInitiatorApp)
                .getMoveEventQueueName();
        messagingService.sentMessageToPlayer(nameOfQueueToSendTo, message);
        return "Game started";
    }

    public String move(String nameOfRequestInitiatorApp, MoveWasMadeMessage message) throws PlayerNotFoundException, MoveNotValidException {
        Move move = new MoveGameOfThree(gameLogic.getNewResultingNumber(), message.getMove());
        message.setResultingNumber(gameLogic.makeMove(move));
        Player opponent = playerService.getOpponentPlayerByPlayerApplicationName(nameOfRequestInitiatorApp);

        if (gameLogic.isWon()) {
            gameLogic = null;
            Player winner = playerService.getPlayerByApplicationName(nameOfRequestInitiatorApp);
            messagingService.sentMessageToPlayer(winner.getGameOverEventQueueName(), new GameOverMessage(GameOverMessage.WIN_RESULT));
            messagingService.sentMessageToPlayer(opponent.getGameOverEventQueueName(), new GameOverMessage(GameOverMessage.LOSE_RESULT));
            gameReserved = false;
            return "1!";
        } else {
            messagingService.sentMessageToPlayer(opponent.getMoveEventQueueName(), message);
            return "Good choice! Waiting for your opponent's respond...";
        }
    }

    private MoveWasMadeMessage mapStartMessageToMoveWasMadeMessage(StartGameMessage startGameMessage) {
        int firstMove = startGameMessage.getInitialNumber();
        int initialResultingNumber = firstMove;
        String playerName = startGameMessage.getPlayerName();
        return new MoveWasMadeMessage(firstMove, initialResultingNumber, playerName);
    }

}
