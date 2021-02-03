package com.vladagorova.playerclient.player;

public interface Player {
    int setInitialNumber();
    int getMove(int currentResultingNumber);
    String getName();
    void setName(String name);
}
