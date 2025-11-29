# Étape 1 : Build du projet
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Création de l'image légère pour lancer l'app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Railway attend ce port
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]