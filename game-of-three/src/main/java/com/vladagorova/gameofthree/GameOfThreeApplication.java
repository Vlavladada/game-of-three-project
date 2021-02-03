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
    }

}
