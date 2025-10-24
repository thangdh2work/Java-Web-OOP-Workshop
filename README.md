# Student Manager – Enhanced (i18n + MySQL + Docker)

## Run with Docker + MySQL
```bash
docker compose up --build
# open http://localhost:8080
# admin / 123456
```

- The app runs with the **mysql** profile (connects to the `mysql` service in compose).
- Switch VI/EN using the button in the **navbar** (after login) and on the **login page**.

## Run with local MySQL (without Docker DB)
1) Install MySQL and create the DB `studentdb`, user `appuser/apppass` (customize as needed).
2) Run the app with the `mysql` profile:
```bash
SPRING_PROFILES_ACTIVE=mysql mvn spring-boot:run
```

## Endpoints
- `/login` – sign in
- `/profile` – personal profile
- `/password/change` – change password
- `/admin/students` – admin page (ROLE_ADMIN only)
- `/swagger-ui.html` – Swagger UI
- `/healthz` – health check

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
