FROM maven:3.9.9-eclipse-temurin-21-alpine as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8080

ARG JAR_FILE=target/*.jar
COPY --from=builder /usr/src/app/target/*.jar /usr/app/target.jar

WORKDIR /app
RUN mkdir -p /app/upload-dir

ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}

ENTRYPOINT ["java","-jar","/usr/app/target.jar"]