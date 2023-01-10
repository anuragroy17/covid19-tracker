FROM openjdk:8-jdk-alpine
MAINTAINER anuragroy.com
COPY target/covid-19-tracker-0.0.1-SNAPSHOT.jar covid-19-tracker.jar
ENTRYPOINT ["java","-jar","/covid-19-tracker.jar"]