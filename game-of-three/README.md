# About

Game-of-three - game logic management service.

Requests, received from players (play-client) are validated, processed, and the result is sent to the player/players. RabbitMQ is used to eliminate errors in case any player is temporary unavailable. 