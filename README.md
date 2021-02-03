# About game-of-three-project

Game-of-three-project allows two independent units – the players to communicate with each other using an API.

* **Structure**

 -- player-client - can act as a game initiator, provides input for the game by sending requests to game-of-three REST API and shows the game outputs to user (currently in terminal, but it could be easily changed. To do so, just create a new presenter implementing PlayerDialogPresenter)

There are two play modes available for the player: auto and manual. 
The mode could be configured by providing the correspondent environment variable: 

`spring_profiles_active=manual` or `spring_profiles_active=auto`

-- game-of-three - game logic management service. 
  
Requests, received from players are validated, processed, and the result is sent to the player/players. RabbitMQ is used to eliminate errors in case any player is temporary unavailable. 
  
 -- eureka-service-registry - Eureka Service registry Spring Boot application used for registration of the services. Helps to eliminate errors when starting player-clients before game-of-three service.


* **Rules**
  
When a player starts, it incepts a random (whole) number and sends it to the second
  player as an approach of starting the game. The receiving player can now always choose
  between adding one of {-1, 0, 1} to get to a number that is divisible by 3. Divide it by three. The
  resulting whole number is then sent back to the original sender.
  The same rules are applied until one player reaches the number 1(after the division).

# Run

* **From your IDE**

Things to be checked before starting the application:

-- rabbitMQ is running (you can use `docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management`)

-- two player-client services are opened. 

-- `spring_profiles_active=manual` or `spring_profiles_active=auto` is determined for both players 

-- player-client `application.properties` are `spring.application.name=player1
server.port=8082` for the first player and `spring.application.name=player2
server.port=8083` for the second.  Ports can be configured as you like, but names should be determined as stated.

* **Using docker-compose.yml**

Please load the docker-compose.yml file and run `docker-compose up`

IMPORTANT: by default active profile for both players is manual, if you want to change it, please don't forget to adjust `SPRING_PROFILES_ACTIVE` variable before running.

  









