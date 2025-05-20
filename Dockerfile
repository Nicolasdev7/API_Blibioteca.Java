# Etapa 1: Build do projeto
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/biblioteca-0.0.1-SNAPSHOT.jar biblioteca.jar
ENTRYPOINT ["java", "-jar", "biblioteca.jar"]
