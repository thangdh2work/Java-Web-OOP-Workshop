# Run with Docker

## Build & run with Compose
```bash
docker compose up --build
```

App listens on http://localhost:8080

Default admin: **admin / 123456**

## Build standalone image
```bash
docker build -t student-mgr:local .
docker run --rm -p 8080:8080 student-mgr:local
```

## Switch to MySQL (optional)
1. Start MySQL service in `docker-compose.yml` (uncomment the mysql section & volume).
2. Update `src/main/resources/application.yml` datasource to MySQL and add dependency `mysql-connector-j` in `pom.xml`.
