package com.vladagorova.gameofthree.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest {

    @Test
    void setPlayersByFirstPlayerApplicationNameWorksCorrectlyWithValidParams() throws PlayerNotFoundException {
        // arrange
        PlayerService playerService = new PlayerService();
        String firstPlayerApplicationName = "player2";
        Player expectedSecondPlayer = new PlayerGameOfThree("player1");

        // act
        playerService.setPlayersByFirstPlayerApplicationName(firstPlayerApplicationName);

        // assert
        assertEquals(
                expectedSecondPlayer,
                playerService.getOpponentPlayerByPlayerApplicationName(firstPlayerApplicationName));
    }

    @Test
    void setPlayersByFirstPlayerApplicationNameThrowsExceptionWithInvalidParams() throws PlayerNotFoundException {
        // assert
        assertThrows(
                IllegalArgumentException.class, this::callSetPlayersByFirstPlayerApplicationNameWithInvalidParams);
    }

    @Test
    void getPlayerByApplicationName() throws PlayerNotFoundException {
        // arrange
        PlayerService playerService = new PlayerService();
        playerService.setPlayersByFirstPlayerApplicationName("player1");
        Player expectedPlayer = new PlayerGameOfThree("player1");

        // act
        Player player = playerService.getPlayerByApplicationName("player1");

        // assert
        assertEquals(expectedPlayer, player);
    }

    @Test
    void getOpponentPlayerMoveQueueByPlayerApplicationName() throws PlayerNotFoundException {
        // arrange
        PlayerService playerService = new PlayerService();
        playerService.setPlayersByFirstPlayerApplicationName("player1");
        String expectedQueueName = "player2.event.in";

        // act
        String queueName = playerService.getOpponentPlayerMoveQueueByPlayerApplicationName("player1");

        // assert
        assertEquals(expectedQueueName, queueName);
    }

    @Test
    void getOpponentPlayerGameOverQueueByPlayerApplicationName() throws PlayerNotFoundException {
        // arrange
        PlayerService playerService = new PlayerService();
        playerService.setPlayersByFirstPlayerApplicationName("player1");
        String expectedQueueName = "player2.game.over";

        // act
        String queueName = playerService.getOpponentPlayerGameOverQueueByPlayerApplicationName("player1");

        // assert
        assertEquals(expectedQueueName, queueName);
    }

    @Test
    void getOpponentPlayerByPlayerApplicationName() throws PlayerNotFoundException {
        // arrange
        PlayerService playerService = new PlayerService();
        playerService.setPlayersByFirstPlayerApplicationName("player1");
        Player expectedPlayer = new PlayerGameOfThree("player2");

        // act
        Player player = playerService.getOpponentPlayerByPlayerApplicationName("player1");

        // assert
        assertEquals(expectedPlayer, player);
    }

    private void callSetPlayersByFirstPlayerApplicationNameWithInvalidParams() {
        // arrange
        PlayerService playerService = new PlayerService();
        String firstPlayerApplicationName = "invalid service";

        // act
        playerService.setPlayersByFirstPlayerApplicationName(firstPlayerApplicationName);
    }

}