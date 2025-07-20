# 👓 Luminova - Sistema de Gestión para Ópticas

Proyecto desarrollado con **Spring Boot** para la gestión de usuarios, roles y servicios en una óptica. Incluye autenticación mediante **JWT**, control de acceso por roles (`ROLE_USER`, `ROLE_ADMIN`) y una interfaz web construida con **Thymeleaf**.

## 🧰 Tecnologías utilizadas

- ☕ Java 17+
- 🌱 Spring Boot
  - Spring Web
  - Spring Security
  - Spring Data JPA
- 🔐 JWT (JSON Web Token)
- 🧠 Lombok
- 🧾 Thymeleaf
- 🐘 Base de datos: MySQL
- 🌐 HTML5, CSS3, Bootstrap
- 📦 Maven

## 🔐 Funcionalidades principales

- Registro e inicio de sesión de usuarios
- Generación de tokens JWT y almacenamiento en cookies
- Gestión de roles: `ROLE_ADMIN` y `ROLE_USER`
- Rutas protegidas según el rol
- Panel de administración para gestionar usuarios
- Frontend responsivo con Bootstrap y Thymeleaf

## 🧑‍💻 Estructura del proyecto

```

app-spring-luminova/
├── src/
│   ├── main/
│   │   ├── java/com/optica/luminova/
│   │   │   ├── admin/
│   │   │   ├── controller/
│   │   │   ├── DTO/
│   │   │   ├── model/
│   │   │   ├── password/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   ├── static/
│   │   │   └── application.properties
└── pom.xml

````

## ⚙️ Configuración inicial

### 1. Clona el repositorio
```bash
git clone https://github.com/tu-usuario/app-spring-luminova.git
cd app-spring-luminova
````

### 2. Configura la base de datos en `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/luminova
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
jwt.secret=claveSecretaJWT123
```

### 3. Ejecuta la aplicación

```bash
./mvnw spring-boot:run
```

## 🔑 Rutas y roles

| Ruta          | Acceso     |
| ------------- | ---------- |
| `/login`      | Público    |
| `/registro`   | Público    |
| `/admin/**`   | Solo ADMIN |
| `/usuario/**` | Solo USER  |
| `/home`       | Ambos      |

## 📸 Capturas

> *(Agrega aquí imágenes de la interfaz si deseas)*

## ✨ Próximas mejoras

* Recuperación de contraseña
* CRUD completo de productos (lentes, monturas, etc.)
* Generación de reportes PDF
* Registro de citas y ventas

## 🧑 Autor

* **Moises Sanchez & Dennys Lozano**
* Ingeniería de Sistemas e Informática
* Proyecto académico - UTP

---

## 📜 Licencia

Este proyecto es de uso educativo. Puedes reutilizarlo y modificarlo con fines no comerciales.
