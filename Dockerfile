FROM openjdk:8-jre-alpine

MAINTAINER Riviewz "contact@riviewz.com"

WORKDIR /usr/local/bin/

COPY ./target/users-ms-0.0.1-SNAPSHOT.jar users-ms.jar

CMD ["java", "-jar", "users-ms.jar"]