package com.vladagorova.gameofthree.gamelogic;

import com.vladagorova.gameofthree.move.Move;
import com.vladagorova.gameofthree.move.MoveNotValidException;

public interface GameLogic {
    int makeMove(Move move) throws MoveNotValidException;
    int getNewResultingNumber();
    void setNewResultingNumber(int newResultingNumber);
    boolean isWon();
}
