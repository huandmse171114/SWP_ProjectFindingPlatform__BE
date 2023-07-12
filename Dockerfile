FROM openjdk:17
EXPOSE 8080
COPY ./target/finhub-backend-swp.jar finhub-backend-swp.jar
CMD [ "java", "-jar", "/finhub-backend-swp.jar" ]