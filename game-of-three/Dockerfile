FROM openjdk:11
MAINTAINER Vladyslava
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} game-of-three-service.jar
ENTRYPOINT ["java","-jar","/game-of-three-service.jar"]