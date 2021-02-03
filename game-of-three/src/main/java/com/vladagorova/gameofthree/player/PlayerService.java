package com.vladagorova.gameofthree.player;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private List<Player> playerList;

    public void setPlayersByFirstPlayerApplicationName(String firstPlayerApplicationName) {
        if (!playerApplicationNameIsValid(firstPlayerApplicationName)) {
            throw new IllegalArgumentException("Invalid application name");
        }
        Player player1 = new PlayerGameOfThree(firstPlayerApplicationName);
        String appNameOfPlayer2 = firstPlayerApplicationName.contains("1") ? "player2" : "player1";
        Player player2 = new PlayerGameOfThree(appNameOfPlayer2);
        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
    }

    public Player getPlayerByApplicationName(String applicationName) throws PlayerNotFoundException {
        for (Player player: playerList) {
            if (player.getApplicationName().equals(applicationName)) {
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    public String getOpponentPlayerMoveQueueByPlayerApplicationName (String applicationName) throws PlayerNotFoundException {
        return getOpponentPlayerByPlayerApplicationName(applicationName).getMoveEventQueueName();
    }

    public String getOpponentPlayerGameOverQueueByPlayerApplicationName (String applicationName) throws PlayerNotFoundException {
        return getOpponentPlayerByPlayerApplicationName(applicationName).getGameOverEventQueueName();
    }

    public Player getOpponentPlayerByPlayerApplicationName(String applicationName) throws PlayerNotFoundException {
        for (Player player: playerList) {
            if (!player.getApplicationName().equals(applicationName)) {
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    private boolean playerApplicationNameIsValid(String applicationName){
        return applicationName.equals("player1") || applicationName.equals("player2");
    }

}
