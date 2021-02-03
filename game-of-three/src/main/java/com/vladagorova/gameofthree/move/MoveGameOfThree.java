package com.vladagorova.gameofthree.move;

import java.util.List;

public class MoveGameOfThree implements Move {
    private final List<Integer> availableMoves = List.of(-1, 0, 1);
    private final int currentResultingNumber;
    private final int selectedValue;

    public MoveGameOfThree(int currentResultingNumber, int selectedValue) {
        this.currentResultingNumber = currentResultingNumber;
        this.selectedValue = selectedValue;
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