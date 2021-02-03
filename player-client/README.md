# About

The player-client microservice depends on other infrastructure which needs to be started first:

* rabbitMQ 

To run service the with a docker please use:

`docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management`  

* eureka-service-registry microservice - is used by the service to register itself and discover game-of-three application instances.

To run service the with a docker please use:

`docker run -p 8761:8761 docker.io/library/eureka-service-registry:0.0.1-SNAPSHOT`

* game-of-three