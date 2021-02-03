package com.vladagorova.playerclient.api.client.entity;

public class StartGameRequest {

    private Integer initialNumber;
    private String playerName;

    public StartGameRequest() {
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
