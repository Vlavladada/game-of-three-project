package com.vladagorova.gameofthree.gamelogic;

import com.vladagorova.gameofthree.move.Move;
import com.vladagorova.gameofthree.move.MoveNotValidException;

public class GameOfThreeLogic implements GameLogic {
    private final int WINNING_NUMBER = 1;

    private int newResultingNumber;

    public GameOfThreeLogic(int initialNumber) {
        this.newResultingNumber = initialNumber;
    }

    @Override
    public int makeMove(Move move) throws MoveNotValidException {
        if (!move.isValid()) {
            throw new MoveNotValidException();
        } else {
            newResultingNumber = getNewResultingNumber(move);
            return newResultingNumber;
        }
    }

    @Override
    public boolean isWon() {
        return newResultingNumber == WINNING_NUMBER;
    }

    private int getNewResultingNumber(Move move) {
        return (move.getCurrentResultingNumber() + move.getSelectedValue()) / 3;
    }

    @Override
    public int getNewResultingNumber() {
        return newResultingNumber;
    }

    @Override
    public void setNewResultingNumber(int newResultingNumber) {
        this.newResultingNumber = newResultingNumber;
    }
}
