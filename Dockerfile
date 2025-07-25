FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/prescripto-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]

