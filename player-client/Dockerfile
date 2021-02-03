FROM openjdk:11
MAINTAINER Vladyslava
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} player-client-service.jar
ENTRYPOINT ["java","-jar","/player-client-service.jar"]