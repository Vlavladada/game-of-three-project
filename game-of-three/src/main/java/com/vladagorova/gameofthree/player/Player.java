package com.vladagorova.gameofthree.player;

public interface Player {
    String getMoveEventQueueName();
    String getGameOverEventQueueName();
    String getApplicationName();
}
