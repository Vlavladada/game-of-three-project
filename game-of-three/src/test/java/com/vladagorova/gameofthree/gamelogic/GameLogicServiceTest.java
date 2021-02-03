package com.vladagorova.gameofthree.gamelogic;

import com.vladagorova.gameofthree.messaging.MessagingService;
import com.vladagorova.gameofthree.player.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameLogicServiceTest {

    @MockBean
    private MessagingService messagingService;
    @MockBean
    private PlayerService playerService;

    @Test
    void gameStartedFirstCallReturnsFalseAndAllNextTrue() {
        // arrange
        GameLogicService logicService = new GameLogicService(messagingService, playerService);

        // act
        boolean firstCallResult = logicService.gameStarted();
        boolean secondCallResult = logicService.gameStarted();
        boolean thirdCallResult = logicService.gameStarted();
        boolean fourthCallResult = logicService.gameStarted();

        // assert
        assertFalse(firstCallResult);
        assertTrue(secondCallResult);
        assertTrue(thirdCallResult);
        assertTrue(fourthCallResult);
    }

}