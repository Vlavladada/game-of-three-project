package com.vladagorova.gameofthree.move;

import java.util.List;

public interface Move {
    List<Integer> getAvailableOptions();
    boolean isValid();
    boolean isValidFirstMove(int move);
    int getSelectedValue();
    int getCurrentResultingNumber();
}
