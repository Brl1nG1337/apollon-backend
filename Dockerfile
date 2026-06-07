# Stage 1: Build-Umgebung
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Laufzeit-Umgebung
FROM eclipse-temurin:17-jre
WORKDIR /app
# Kopiere nur das fertige JAR-File aus dem Build-Container
COPY --from=build /app/target/*.jar app.jar
# Startbefehl
ENTRYPOINT ["java", "-jar", "app.jar"]