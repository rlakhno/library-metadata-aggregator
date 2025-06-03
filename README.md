# 📚 Library Metadata Aggregator

A secure, authenticated REST API built with Spring Boot that aggregates book metadata from external APIs (Google Books) and stores them in a PostgreSQL database. Includes simulated ILS (Integrated Library System) features like lending records and user activity tracking.

---

## 🚀 Features

- ✅ **User Registration & Login** (JWT-based authentication)
- 📚 **Book Search & Fetch by ISBN**
- 🧑‍💻 **Top Active Users** by fetched books
- 🔐 **Role-Based Access Control**
- 🔄 **Swagger UI (OpenAPI)** for interactive API documentation
- 💾 **Lending Simulation** (borrow/return tracking)
- 👥 **User Activity Overview** with role info
- 📦 PostgreSQL for data persistence

---

## 📂 Project Structure

```
com.example.libraryapi
├── controller
│ ├── AuthController.java
│ ├── BookController.java
│ ├── LendingController.java
│ ├── UserController.java
│ └── HomeController.java
├── model
│ ├── Book.java
│ ├── Lending.java
│ └── User.java
├── repository
│ ├── BookRepository.java
│ ├── LendingRepository.java
│ └── UserRepository.java
├── service
│ ├── BookService.java
│ ├── LendingService.java
│ └── UserService.java
├── security
│ ├── JwtTokenProvider.java
│ ├── JwtAuthenticationFilter.java
│ ├── SecurityConfig.java
│ └── CustomUserDetailsService.java
└── config
└── OpenAPIConfig.java
```


---

## 🛠️ Technologies

- Java 17
- Spring Boot 3+
- Spring Security
- Spring Data JPA
- PostgreSQL
- Swagger (springdoc-openapi)
- JWT for Auth
- Maven

---

## 🧪 API Documentation

Interactive API docs available at:


Make sure to **click “Authorize”** and paste your JWT token before using protected endpoints.

---

## 🧾 Sample Endpoints

### 🔐 Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

### 📚 Books
- `GET /api/books`
- `GET /api/books/search?title=java`
- `POST /api/books/fetch?isbn=9780134685991`
- `GET /api/books/top-users`

### 👤 Users
- `GET /api/users/activity-role`

### 📖 Lending
- `POST /api/lendings`
- `GET /api/lendings`
- `GET /api/lendings/my`

---

## 🧰 Getting Started

### ✅ Prerequisites

- Java 17
- Maven
- PostgreSQL

### ⚙️ Setup

**Clone the repo**
   
  ` git clone https://github.com/your-username/library-api.git
   cd library-api`

## ✅ Getting Started

### 1. Configure `application.properties`

```
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```
### 2. Run the app

- `mvn spring-boot:run`

### 🛡️ JWT Auth Instructions

🔐 Register a user:

- `/api/auth/register`

🔐 Log in:

- `/api/auth/login`

➡️ Copy the JWT token from the login response.

Go to Swagger UI:

- `http://localhost:8080/swagger-ui.html`

➡️ Click Authorize and paste the token in the following format:

- `Bearer YOUR_TOKEN_HERE`

👨‍💻 Author
Rouslan Lakhno
GitHub Profile:
- `https://github.com/`



