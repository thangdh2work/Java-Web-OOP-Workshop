# StudentMgr – Docker + MySQL + i18n fix

## Run with Docker + MySQL
```bash
docker compose up --build
# App: http://localhost:8080
# Login: admin / 123456
```
Profile active: `mysql` (see `SPRING_PROFILES_ACTIVE` in compose).

## Local run with H2 (default)
```bash
mvn spring-boot:run
# http://localhost:8080/login
```

## Switch language VI/EN
- Navbar (khi đăng nhập): nút **VI/EN** đổi ngôn ngữ ngay.
- Trang Đăng nhập: nút **VI/EN** tại góc dưới form.
Config i18n: `spring.messages.basename=messages` (VI = `messages.properties`, EN = `messages_en.properties`).
