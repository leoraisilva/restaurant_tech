ARG ARG_POSTGRES_ENV

FROM maven:3.8.8-eclipse-temurin-21 AS build

ENV POSTGRES_ENV=${ARG_POSTGRES_ENV}

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/restaurant-0.0.1-SNAPSHOT.jar /app/restaurant.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/restaurant.jar"]