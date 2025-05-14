# Etapa 1: Usar uma imagem base do OpenJDK
FROM openjdk:21-jdk-slim as build

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven para o contêiner
COPY target/biblioteca-0.0.1-SNAPSHOT.jar biblioteca.jar

# Etapa 2: Definir o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "biblioteca.jar"]
