package com.vladagorova.playerclient.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component("player")
@Profile("auto")
public class PlayerAutoPlayModeImplementation implements Player {

    private final Logger logger = LoggerFactory.getLogger(PlayerAutoPlayModeImplementation.class);
    private String name = "Friendly Computer";

    @Override
    public int setInitialNumber() {
        Random random = new Random();
        // int initialNumber = random.nextInt((Integer.MAX_VALUE - 2) + 1) + 2;
        int initialNumber = random.nextInt((500 - 2) + 1) + 2;
        logger.info(String.valueOf(initialNumber));
        return initialNumber;
    }

    @Override
    public int getMove(int resultingNumber) {
        List<Integer> availableMoveOptions = List.of(-1, 0, 1); // TODO
        for (int moveOption : availableMoveOptions) {
            if ((resultingNumber + moveOption) % 3 == 0) {
                logger.info(String.valueOf(moveOption));
                return moveOption;
            }
        }
        throw new RuntimeException("Sorry, something went wrong, please try again!");
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
