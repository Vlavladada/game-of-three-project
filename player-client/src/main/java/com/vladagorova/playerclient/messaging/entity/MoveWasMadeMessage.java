package com.vladagorova.playerclient.messaging.entity;

public class MoveWasMadeMessage {
    private int move;
    private int resultingNumber;
    private String playerName;

    public MoveWasMadeMessage() {
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
