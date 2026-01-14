# 🏦 Banking System – Spring Boot Backend

A backend banking system built using **Spring Boot**, **Spring Data JPA**, **MySQL**, and **RESTful APIs**.  
This project was originally implemented in **Core Java** and later migrated to **Spring Boot** to follow industry-standard backend architecture.

---

## 🚀 Features

- Create users
- Create bank accounts
- Deposit money
- Withdraw money
- Transfer money between accounts (atomic & transactional)
- Check account balance

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **REST APIs**
- **Maven**
- **Postman** (for API testing)

---

## 🧩 Project Architecture

The project follows a **layered architecture**:

- **Controller Layer**  
  Handles REST API requests and responses.

- **Service Layer**  
  Contains business logic like deposit, withdraw, and transfer.

- **Repository Layer**  
  Uses Spring Data JPA to interact with the database.

- **Model Layer**  
  JPA entities representing database tables.

---

## 🔁 Key Banking Operations

### 💰 Deposit & Withdraw
- Updates account balance safely
- Validates amount and account existence

### 🔁 Account-to-Account Transfer
- Implemented using `@Transactional`
- Ensures **atomicity** (all-or-nothing transfer)
- Prevents partial updates and data inconsistency

---

## 🌐 REST API Endpoints

### 👤 User APIs
| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/users` | Create a new user |

### 🏦 Account APIs
| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/accounts` | Create account for a user |
| PUT | `/accounts/deposit` | Deposit money |
| PUT | `/accounts/withdraw` | Withdraw money |
| PUT | `/accounts/transfer` | Transfer money between accounts |
| GET | `/accounts/balance` | Check account balance |

---

## ⚙️ Database Design

- Uses **MySQL**
- Auto-increment primary keys for internal use
- Business identifiers:
  - `userId`
  - `accountNumber`
- Referential integrity enforced using foreign keys

---

## ▶️ How to Run the Project

1. Clone the repository
2. Create a MySQL database
3. Configure database details in `application.properties`
4. Run the application using:
   ```bash
   mvn spring-boot:run

## 👩‍💻 Author  
Divya Sonawane  
Backend Developer | Java | Spring Boot | MySQL

