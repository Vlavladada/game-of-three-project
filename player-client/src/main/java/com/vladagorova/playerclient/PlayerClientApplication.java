package com.vladagorova.playerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class PlayerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerClientApplication.class, args);
	}

}
