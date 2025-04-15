FROM maven:3.9.9-eclipse-temurin-21-alpine as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/*.jar
COPY --from=builder /usr/src/app/target/*.jar /usr/app/target.jar

## Create a non-root user and group
RUN addgroup -S spring && adduser -S spring -G spring

## Set the non-root user as the default user
USER spring:spring
WORKDIR /app
RUN mkdir -p /app/upload-dir

ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}

ENTRYPOINT ["java","-jar","/usr/app/target.jar"]
EXPOSE 8080