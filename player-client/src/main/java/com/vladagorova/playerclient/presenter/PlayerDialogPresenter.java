package com.vladagorova.playerclient.presenter;

public interface PlayerDialogPresenter {

    void presentGameWonMessage();

    void presentGameLostMessage();

    void presentRequestNewPlayRoundMessage();

    void presentGameAlreadyStartedMessage();

    void presentNoGameOfThreeServiceAvailableMessage();

    void presentInitialNumberRequestMessage();

    void presentMoveRequestMessage();

    void presentOpponentMoveInfoMessage(String opponentName, int moveValue, int resultingNumber);

    void presentGameStartRequestResult(String requestResult);

    void presentMakeMoveRequestResult(String requestResult);

    void presentMakeMoveRequestException();
}
