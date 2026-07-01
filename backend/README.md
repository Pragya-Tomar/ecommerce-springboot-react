# E-commerce Backend — Core (Auth + Security)

Spring Boot 3.3 / Java 17 / Maven backend. This is the **auth & security core** — user
registration, login, JWT issuing/validation, role-based authorization, and the exception
handling layer everything else will build on (payments, products, orders next).

## What's included

- `User` / `Role` entities (many-to-many roles: `ROLE_USER`, `ROLE_ADMIN`)
- JWT auth: `JwtUtil` (sign/verify), `JwtAuthFilter` (per-request token check), `JwtAuthEntryPoint` (clean 401 JSON)
- `SecurityConfig`: stateless sessions, BCrypt passwords, CORS for the React frontend, method-level `@PreAuthorize`
- Custom exceptions + `GlobalExceptionHandler` → every error returns consistent JSON, never a raw stack trace
- `/api/auth/register`, `/api/auth/login`, `/api/users/me`, `/api/users/admin/all` (paginated, admin-only)
- Profiles: `beta` (verbose logging, local MySQL), `prod` (strict, `ddl-auto: validate`), `test` (H2 in-memory)
- Unit tests (Mockito) + repository test (`@DataJpaTest`) + full register→login integration test (`MockMvc`)

## Setup in IntelliJ

1. **Open** the `ecommerce-backend` folder as a Maven project (IntelliJ auto-detects `pom.xml`).
2. Install **Lombok plugin** (Settings → Plugins) and enable annotation processing
   (Settings → Build → Compiler → Annotation Processors → check "Enable annotation processing").
3. **Create a MySQL database** for beta: `CREATE DATABASE ecommerce_beta;`
4. Copy `.env.example` → set real values, then in your Run Configuration for
   `EcommerceBackendApplication`, add these under **Environment variables** (IntelliJ has a
   built-in "Insert .env file" option in newer versions, or paste manually):
   ```
   SPRING_PROFILES_ACTIVE=beta;DB_URL=jdbc:mysql://localhost:3306/ecommerce_beta;DB_USERNAME=root;DB_PASSWORD=yourpass;JWT_SECRET=some-long-random-string
   ```
5. Run `EcommerceBackendApplication`. On first boot, `RoleSeeder` inserts `ROLE_USER`/`ROLE_ADMIN`.

## Switching profiles

- **Beta** (default): `SPRING_PROFILES_ACTIVE=beta` — verbose logs, `ddl-auto: update`.
- **Production**: `SPRING_PROFILES_ACTIVE=prod` — quiet logs, `ddl-auto: validate` (schema must
  already exist — never let prod auto-alter tables).
- **Test**: activated automatically by `@ActiveProfiles("test")` in test classes — uses H2
  in-memory DB, no MySQL required to run tests.

## Try it

```bash
# Register
curl -X POST localhost:8080/api/auth/register -H "Content-Type: application/json" \
  -d '{"username":"pragya","email":"pragya@example.com","password":"securePass123"}'

# Login → returns a JWT
curl -X POST localhost:8080/api/auth/login -H "Content-Type: application/json" \
  -d '{"username":"pragya","password":"securePass123"}'

# Use the token
curl localhost:8080/api/users/me -H "Authorization: Bearer <token>"
```

## Run tests

```bash
./mvnw test
```

## Next up

- Product/Category entities + pagination
- Order + Razorpay payment module (`@Transactional`, signature verification)
- React frontend (login split-panel, navbar, carousel — matching your #7FA9D9 / #ff66b2 / lavender palette)
