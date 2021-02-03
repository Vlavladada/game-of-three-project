package com.vladagorova.gameofthree.move;

import java.util.List;

public interface Move {
    List<Integer> getAvailableOptions();
    boolean isValid();
    int getSelectedValue();
    int getCurrentResultingNumber();
}
