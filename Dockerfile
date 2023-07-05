FROM openjdk
COPY ./target/finhub-backend-0.0.1-SNAPSHOT /apps/
CMD [ "java", "-jar", "/apps/finhub-backend-0.0.1-SNAPSHOT" ]