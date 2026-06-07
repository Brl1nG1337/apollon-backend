# --- STAGE 1: Build ---
# Wir nutzen Maven, um das Projekt zu bauen
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# 1. pom.xml kopieren und Dependencies laden (Caching-Optimierung)
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Den Sourcecode kopieren und bauen
COPY src ./src
# Hinweis: Falls du noch andere Ordner/Dateien brauchst, ggf. hier ergänzen

# --- WICHTIG: Speicherbegrenzung für den Raspberry Pi ---
ENV MAVEN_OPTS="-Xmx512m -Xss512k"

# 3. Build ausführen (Tests überspringen, um Zeit/Ressourcen zu sparen)
RUN mvn clean package -DskipTests

# --- STAGE 2: Runtime ---
# Wir nutzen nur die JRE (Java Runtime Environment) für das kleine, schnelle Image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Kopiere das gebaute .jar File aus der Build-Stage
# Passe den Namen hinter /app/target/ an, falls dein Projekt einen anderen Namen hat
COPY --from=build /app/target/*.jar app.jar

# App starten
ENTRYPOINT ["java", "-jar", "app.jar"]