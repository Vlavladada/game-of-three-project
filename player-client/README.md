# About

Play-client service can act as a game initiator, provides input for the game by sending requests to game-of-three REST API and shows the game outputs to user (currently in terminal, but it could be easily changed. To do so, just create a new presenter implementing PlayerDialogPresenter)

There are two play modes available for the player: auto and manual.
The mode could be configured by providing the correspondent environment variable:

`spring_profiles_active=manual` or `spring_profiles_active=auto`

The player-client microservice depends on rabbitMQ which needs to be started first.