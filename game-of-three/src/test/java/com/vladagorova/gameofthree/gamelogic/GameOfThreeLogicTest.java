package com.vladagorova.gameofthree.gamelogic;

import com.vladagorova.gameofthree.gamelogic.GameOfThreeLogic;
import com.vladagorova.gameofthree.move.Move;
import com.vladagorova.gameofthree.move.MoveGameOfThree;
import com.vladagorova.gameofthree.move.MoveNotValidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfThreeLogicTest {

    @Test
    void makeMoveReturnsCorrectValueWhenCalledWithValidParameters() throws MoveNotValidException {
        // arrange
        GameOfThreeLogic game = new GameOfThreeLogic(15);
        Move move = new MoveGameOfThree(15, 0);
        int expectedResultingValue = 5;

        // act
        int resultingValue = game.makeMove(move);

        // assert
        assertEquals(expectedResultingValue, resultingValue);
    }

    @Test
    void makeMoveThrowsExceptionWhenMoveIsNotValid() {
        // assert
        assertThrows(MoveNotValidException.class, this::callMakeMoveWithValidResultingNumberAndInvalidMove);
    }

    @Test
    void makeMoveThrowsExceptionWhenResultingNumberIsNotValid() {
        // assert
        assertThrows(MoveNotValidException.class, this::callMakeMoveWithInvalidResultingNumberAndValidMove);
    }


    @Test
    void isWonReturnsTrueInWinningCase() throws MoveNotValidException {
        // arrange
        GameOfThreeLogic game = new GameOfThreeLogic(3);
        Move move = new MoveGameOfThree(3, 0);

        // act
        game.makeMove(move);

        // assert
        assertTrue(game.isWon());
    }

    @Test
    void isWonReturnsFalseInNotWinningCase() throws MoveNotValidException {
        // arrange
        GameOfThreeLogic game = new GameOfThreeLogic(6);
        Move move = new MoveGameOfThree(6, 0);

        // act
        game.makeMove(move);

        // assert
        assertFalse(game.isWon());
    }

    private void callMakeMoveWithValidResultingNumberAndInvalidMove() throws MoveNotValidException {
        GameOfThreeLogic game = new GameOfThreeLogic(0);
        Move move = new MoveGameOfThree(0, 6);
        game.makeMove(move);
    }

    private void callMakeMoveWithInvalidResultingNumberAndValidMove() throws MoveNotValidException {
        GameOfThreeLogic game = new GameOfThreeLogic(15);
        Move move = new MoveGameOfThree(15, 1);
        game.makeMove(move);
    }

}