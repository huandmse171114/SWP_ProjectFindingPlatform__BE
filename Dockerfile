FROM openjdk
EXPOSE 8080
COPY ./target/finhub-backend-0.0.1-SNAPSHOT.jar finhub-backend-0.0.1-SNAPSHOT.jar
CMD [ "java", "-jar", "/finhub-backend-0.0.1-SNAPSHOT.jar" ]