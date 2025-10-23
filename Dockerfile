# -------- Build stage --------
FROM maven:3.9-eclipse-temurin-21 AS build

# 
WORKDIR /workspace

COPY pom.xml .

RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests dependency:go-offline

COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests package

# -------- Runtime stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/target/*-SNAPSHOT.jar /app/app.jar

CMD [ "exec java -jar /app/app.jar" ]