FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8080

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

WORKDIR /app
RUN mkdir -p /app/upload-dir

CMD ["java","-jar","/app.jar"]