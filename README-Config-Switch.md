# Switched to .properties to avoid YAML DuplicateKey issues

We removed `application.yml`/`application-mysql.yml` and replaced them with
`application.properties` and `application-mysql.properties`.

## Rebuild with no cache (important if Docker cached old YAML files)
```bash
docker compose down -v
docker compose build --no-cache
docker compose up
```

## Local run (H2 default)
```bash
mvn spring-boot:run
```

## Docker (MySQL profile auto via compose)
```bash
docker compose up --build
```
