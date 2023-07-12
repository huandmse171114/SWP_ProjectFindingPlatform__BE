FROM openjdk:17 AS build
COPY ./target/finhub-backend-swp.jar finhub-backend-swp.jar
EXPOSE 8080
RUN mvn clean package -DskipTests
RUN [ "java", "-jar", "/finhub-backend-swp.jar" ]
