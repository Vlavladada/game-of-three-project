package com.vladagorova.gameofthree.messaging.entity;

public class StartGameMessage {
    private Integer initialNumber;
    private String playerName;

    public StartGameMessage() {
    }

    public StartGameMessage(Integer initialNumber, String playerName) {
        this.initialNumber = initialNumber;
        this.playerName = playerName;
    }

    public Integer getInitialNumber() {
        return initialNumber;
    }

    public void setInitialNumber(Integer initialNumber) {
        this.initialNumber = initialNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
