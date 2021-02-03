package com.vladagorova.playerclient.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("player")
@Profile("manual")
public class PlayerManualPlayModeImplementation implements Player {

    private final Logger logger = LoggerFactory.getLogger(PlayerManualPlayModeImplementation.class);
    private final Scanner scanner = new Scanner(System.in);
    private String name = "Incognito Player";

    @Override
    public int setInitialNumber() {
        return scanner.nextInt();
    }

    @Override
    public int getMove(int currentResultingNumber) {
        return scanner.nextInt();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
