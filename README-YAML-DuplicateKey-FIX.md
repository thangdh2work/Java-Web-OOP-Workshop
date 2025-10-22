# Fix for SnakeYAML DuplicateKeyException in Docker

Root cause: Spring Boot loads external config from the working directory (`/app`) before classpath.
If the Docker image contains your *source tree* (e.g. `/app/src/main/resources/application.yml`),
that YAML is picked up and can cause `DuplicateKeyException`.

Solution in this Dockerfile:
- Multi-stage build: runtime image contains **only** `/app/app.jar`
- No source files are copied into runtime image
- Entry script fails fast if any `*.yml` / `*.yaml` exists in `/app`

## Rebuild clean
```bash
docker compose down -v
docker compose build --no-cache
docker compose up
```

## Verify no YAML next to the jar inside the container
```bash
docker exec -it student-mgr sh -c 'ls -la /app && jar tf /app/app.jar | grep -E "application.*ya?ml" || true'
```
