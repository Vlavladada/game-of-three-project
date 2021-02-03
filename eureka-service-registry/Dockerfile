FROM openjdk:11
MAINTAINER Vladyslava
EXPOSE 8761
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka-service-registry.jar
ENTRYPOINT ["java","-jar","/eureka-service-registry.jar"]