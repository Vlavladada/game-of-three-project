package com.vladagorova.gameofthree.player;

import java.util.Objects;

public class PlayerGameOfThree implements Player {

    private final String applicationName;
    private final String moveEventQueueName;
    private final String gameOverQueueName;

    public PlayerGameOfThree(String applicationName) {
        this.applicationName = applicationName;
        moveEventQueueName = applicationName + ".event.in";
        this.gameOverQueueName = applicationName + ".game.over";
    }

    @Override
    public String getMoveEventQueueName() {
        return moveEventQueueName;
    }

    @Override
    public String getGameOverEventQueueName() {
        return gameOverQueueName;
    }

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerGameOfThree that = (PlayerGameOfThree) o;
        return Objects.equals(applicationName, that.applicationName) && Objects.equals(moveEventQueueName, that.moveEventQueueName) && Objects.equals(gameOverQueueName, that.gameOverQueueName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationName, moveEventQueueName, gameOverQueueName);
    }
}
