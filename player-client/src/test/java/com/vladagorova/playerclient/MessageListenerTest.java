package com.vladagorova.playerclient;

import com.vladagorova.playerclient.api.client.PlayService;
import com.vladagorova.playerclient.messaging.MessageListener;
import com.vladagorova.playerclient.messaging.entity.GameOverMessage;
import com.vladagorova.playerclient.messaging.entity.MoveWasMadeMessage;
import com.vladagorova.playerclient.player.Player;
import com.vladagorova.playerclient.presenter.PlayerDialogPresenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessageListenerTest {

    @Mock
    private Player player;
    @Mock
    private PlayService playService;
    @Mock
    private PlayerDialogPresenter dialogPresenter;

    @Test
    void moveWasMadeMessageListenerMakesCorrectCallsToPlayService() {
        // arrange
        MessageListener messageListener = new MessageListener(player, playService, dialogPresenter);
        int initialResultingNumber = 18;
        MoveWasMadeMessage message = new MoveWasMadeMessage();
        message.setResultingNumber(initialResultingNumber);
        Mockito.when(player.getMove(initialResultingNumber)).thenReturn(0);
        Mockito.doNothing().when(playService).makeMove(Mockito.any());

        // act
        messageListener.moveWasMadeMessageListener(message);

        // assert
        Mockito.verify(player, Mockito.times(1)).getMove(initialResultingNumber);
        Mockito.verify(playService, Mockito.times(1)).makeMove(0);
    }

    @Test
    void gameOverMessageListenerRequestsNewPlayRound() {
        // arrange
        MessageListener messageListener = new MessageListener(player, playService, dialogPresenter);
        GameOverMessage gameOverMessage = new GameOverMessage();
        gameOverMessage.setResult(GameOverMessage.WIN_RESULT);

        // act
        messageListener.gameOverMessageListener(gameOverMessage);

        // assert
        Mockito.verify(playService, Mockito.times(1)).requestNewPlayRound();
    }

}