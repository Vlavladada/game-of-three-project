package com.vladagorova.playerclient.player;

import com.vladagorova.playerclient.player.Player;
import com.vladagorova.playerclient.player.PlayerAutoPlayModeImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerAutoPlayModeImplementationTest {

    @Test
    void setInitialNumberReturnsOnlyWholePositiveNumbersGreaterThenOne() {
        // arrange
        Player player = new PlayerAutoPlayModeImplementation();
        int number;
        int quantityOfChecks = 5;

        // act
        for (int i = 0; i < quantityOfChecks; i++) {
            number = player.setInitialNumber();

            // assert
            if (number <= 1) {
                fail();
            }
        }
    }

    @Test
    void getMoveReturnsOnlyValidMoves() {
        // arrange
        Player player = new PlayerAutoPlayModeImplementation();
        int initialResultingNumber1 = 16;
        int initialResultingNumber2 = 9;
        int initialResultingNumber3 = 23;
        int expectedMoveValue1 = -1;
        int expectedMoveValue2 = 0;
        int expectedMoveValue3 = 1;

        // act
        int moveValue1 = player.getMove(initialResultingNumber1);
        int moveValue2 = player.getMove(initialResultingNumber2);
        int moveValue3 = player.getMove(initialResultingNumber3);

        // assert
        assertEquals(expectedMoveValue1, moveValue1);
        assertEquals(expectedMoveValue2, moveValue2);
        assertEquals(expectedMoveValue3, moveValue3);
    }
}