# Etapa 1: Construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar API-Estado-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "API-Estado-1.0.jar"]