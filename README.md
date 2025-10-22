# Student Manager – Enhanced (i18n + MySQL + Docker)

## Chạy bằng Docker + MySQL
```bash
docker compose up --build
# mở http://localhost:8080
# admin / 123456
```

- App chạy với profile **mysql** (kết nối service `mysql` trong compose).
- Đổi VI/EN bằng nút ở **navbar** (sau khi login) và ở **login page**.

## Chạy với MySQL local (không dùng Docker DB)
1) Cài MySQL và tạo DB `studentdb`, user `appuser/apppass` (tuỳ chỉnh).
2) Chạy app với profile `mysql`:
```bash
SPRING_PROFILES_ACTIVE=mysql mvn spring-boot:run
```

## Các đường dẫn
- `/login` – đăng nhập
- `/profile` – hồ sơ cá nhân
- `/password/change` – đổi mật khẩu
- `/admin/students` – trang admin (chỉ ROLE_ADMIN)
- `/swagger-ui.html` – Swagger UI
- `/healthz` – Health check
