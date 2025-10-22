# -------- Build stage --------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy only pom first to leverage dependency cache
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests dependency:go-offline

# Copy source and build
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests package

# Optional: fail fast if any YAML ends up inside the fat jar
RUN JAR=$(ls target/*-SNAPSHOT.jar | head -n1) &&     (jar tf "$JAR" | grep -E 'application.*ya?ml' && echo 'ERROR: YAML found inside jar' && exit 1 || echo 'OK: No YAML inside jar')

# -------- Runtime stage --------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy only the built jar (no source tree)
COPY --from=build /workspace/target/*-SNAPSHOT.jar /app/app.jar

# Sanity check at runtime: ensure no stray YAML next to jar
RUN echo '#!/bin/sh' > /app/entrypoint.sh &&     echo 'set -e' >> /app/entrypoint.sh &&     echo 'if ls /app/*.yml /app/*.yaml >/dev/null 2>&1; then echo "ERROR: External YAML found in /app. Remove them to avoid SnakeYAML errors."; ls -la /app/*.yml /app/*.yaml; exit 1; fi' >> /app/entrypoint.sh &&     echo 'exec java -jar /app/app.jar' >> /app/entrypoint.sh &&     chmod +x /app/entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]
