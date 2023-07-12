# FROM openjdk:17 AS build
# COPY ./target/finhub-backend-swp.jar finhub-backend-swp.jar
# EXPOSE 8080
# RUN mvn clean package -DskipTests
# RUN [ "java", "-jar", "/finhub-backend-swp.jar" ]


FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /target/finhub-backend-swp.jar finhub-backend-swp.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/finhub-backend-swp.jar" ]