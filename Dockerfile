
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar
ARG WEBAPP_DIR=src/main/webapp/

WORKDIR /usr/local/jsf-web-application

COPY ${JAR_FILE} ./target/app.jar
COPY ${WEBAPP_DIR} ./src/main/webapp/

#ENTRYPOINT ["java", "-jar", "target/app.jar]
