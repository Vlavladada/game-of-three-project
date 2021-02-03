package com.vladagorova.gameofthree.move;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveGameOfThreeTest {

    @Test
    void getAvailableOptions() {
        // arrange
        MoveGameOfThree move = new MoveGameOfThree(9,0);
        List<Integer> expectedOptionsList = List.of(-1, 0, 1);

        // act
        List<Integer> optionsList = move.getAvailableOptions();

        // assert
        assertArrayEquals(expectedOptionsList.toArray(), optionsList.toArray());
    }

    @Test
    void isValidReturnsTrueWhenValidNumberIsProvided() {
        // arrange
        MoveGameOfThree move = new MoveGameOfThree(10, -1);

        // act
        boolean validationResult = move.isValid();

        // assert
        assertTrue(validationResult);
    }

    @Test
    void isValidReturnsFalseWhenInvalidNumberIsProvided() {
        // arrange
        MoveGameOfThree move1 = new MoveGameOfThree(11, 7);
        MoveGameOfThree move2 = new MoveGameOfThree(11, 0);

        // act
        boolean validationResult1 = move1.isValid();
        boolean validationResult2 = move2.isValid();

        // assert
        assertFalse(validationResult1);
        assertFalse(validationResult2);
    }

}