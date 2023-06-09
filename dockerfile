#
# First stage
#

FROM maven:3.9.2-amazoncorretto-20 AS build

COPY src /home/app/src
COPY pom.xml /home/app

ARG REDISHOST
ARG REDISPORT
ARG REDISUSER
ARG REDISPASSWORD

RUN mvn -f /home/app/pom.xml clean package

#
# second stage
#

FROM openjdk:20-alpine

ARG REDISHOST
ARG REDISPORT
ARG REDISUSER
ARG REDISPASSWORD

COPY --from=build /home/app/target/ssf-assessment-0.0.1-SNAPSHOT.jar /usr/local/lib/ssf-assessment.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/ssf-assessment.jar"]
