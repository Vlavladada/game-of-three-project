package com.vladagorova.gameofthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

@EnableEurekaClient
@SpringBootApplication
public class GameOfThreeApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GameOfThreeApplication.class, args);

        /*
        Player player2 = new PlayerAutoPlayModeImplementation();
        Player player1;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 to start the game in manual mode or 2 to watch demo");
            int enteredNumber = scanner.nextInt();
            if (enteredNumber == 2) {
                player1 = new PlayerAutoPlayModeImplementation();
            } else {
                player1 = new PlayerManualPlayModeImplementation();
            }
            List<Player> players = List.of(player2, player1);
            int initialNumber = player1.setInitialNumber();
            Game game = new GameRuleOfThreeImplementation(initialNumber);
            System.out.println("Player 1 started the game with number: " + initialNumber);
            int currentNumber = initialNumber;
            while (!game.isWon()) {
                for (Player player: players) {
                    int move = player.getMove(currentNumber);
                    System.out.println("Player " + player.getName() + " entered " + move);
                    game.makeMove(new MoveGoTImplementation(move));
                    currentNumber = game.getResultingNumber();
                    System.out.println("Resulting value: " + currentNumber);
                    if (game.isWon()) {
                        System.out.println("Player " + player.getName() + " wins!");
                        break;
                    }
                }
            }
        } */

    }

}
