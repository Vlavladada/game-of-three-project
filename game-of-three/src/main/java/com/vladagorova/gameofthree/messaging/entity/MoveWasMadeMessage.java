package com.vladagorova.gameofthree.messaging.entity;

public class MoveWasMadeMessage {
    private int move;
    private int resultingNumber;
    private String playerName;

    public MoveWasMadeMessage() {
    }

    public MoveWasMadeMessage(int move, int resultingNumber, String playerName) {
        this.move = move;
        this.resultingNumber = resultingNumber;
        this.playerName = playerName;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getResultingNumber() {
        return resultingNumber;
    }

    public void setResultingNumber(int resultingNumber) {
        this.resultingNumber = resultingNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
