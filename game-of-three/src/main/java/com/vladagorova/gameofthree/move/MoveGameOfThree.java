package com.vladagorova.gameofthree.move;

import java.util.List;

public class MoveGameOfThree implements Move {
    private final List<Integer> availableMoves = List.of(-1, 0, 1);
    private int currentResultingNumber;
    private int selectedValue;

    public MoveGameOfThree(int currentResultingNumber, int selectedValue) {
        this.currentResultingNumber = currentResultingNumber;
        this.selectedValue = selectedValue;
    }

    public MoveGameOfThree() {
    }

    @Override
    public List<Integer> getAvailableOptions() {
        return availableMoves;
    }

    @Override
    public boolean isValid() {
        return isSelectedValueInAvailableMovesList(selectedValue)
                && (currentResultingNumber + selectedValue) % 3 == 0;
    }

    @Override
    public boolean isValidFirstMove(int move) {
        return move > 1;
    }

    @Override
    public int getSelectedValue() {
        return selectedValue;
    }

    @Override
    public int getCurrentResultingNumber() {
        return currentResultingNumber;
    }

    private boolean isSelectedValueInAvailableMovesList(int selectedMove) {
        return availableMoves.contains(selectedValue);
    }

}