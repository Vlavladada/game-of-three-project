package com.vladagorova.gameofthree.messaging.entity;

public class GameOverMessage {
    public static final String WIN_RESULT = "won";
    public static final String LOSE_RESULT = "lost";

    private String result;

    public GameOverMessage() {
    }

    public GameOverMessage(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
