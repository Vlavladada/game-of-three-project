package com.vladagorova.playerclient.presenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlayerDialogConsolePresenter implements PlayerDialogPresenter {

    private Logger logger = LoggerFactory.getLogger(PlayerDialogConsolePresenter.class);

    @Override
    public void presentGameWonMessage() {
        logger.info("Congratulations! It's a clear win!");
    }

    @Override
    public void presentGameLostMessage() {
        logger.info("Sorry, you lose :(");
    }

    @Override
    public void presentRequestNewPlayRoundMessage() {
        logger.info("One more game? :)");
    }

    @Override
    public void presentGameAlreadyStartedMessage() {
        logger.info("Sorry, game has already started. Other player was faster, than you ;)");
    }

    @Override
    public void presentNoGameOfThreeServiceAvailableMessage() {
        logger.error("Failed to find available instances for game-of-three");
    }

    @Override
    public void presentInitialNumberRequestMessage() {
        logger.info("Please enter first number to start the game: ");
    }

    @Override
    public void presentMoveRequestMessage() {
        logger.info("Your turn! Please choose -1, 0 or 1 : ");
    }

    @Override
    public void presentOpponentMoveInfoMessage(String opponentName, int moveValue, int resultingNumber) {
        logger.info("Player " + opponentName + " made a move: " + moveValue);
        logger.info("Resulting number: " + resultingNumber);
    }

    @Override
    public void presentGameStartRequestResult(String requestResult) {
        logger.info(requestResult);
    }

    @Override
    public void presentMakeMoveRequestResult(String requestResult) {
        logger.info(requestResult);
    }

    @Override
    public void presentMakeMoveRequestException() {
        logger.error("Please choose another option to get a number divisible by 3 from the following options: -1, 0, 1");
    }
}
