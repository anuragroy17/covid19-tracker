#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:8-jdk-alpine
MAINTAINER anuragroy.com
COPY --from=build /target/covid-19-tracker-0.0.1-SNAPSHOT.jar covid-19-tracker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/covid-19-tracker.jar"]