# Dockerfile (backend)
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
COPY data/ /app/data/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
