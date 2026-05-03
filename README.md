# WhatsApp Clone - Spring Boot Chat Application

A simple WhatsApp-style chat application built using **Spring Boot**, **Java 17**, **MySQL**, **HTML/CSS/JavaScript**, **JWT Authentication**, **Docker**, and **AWS deployment support**.

---

## Project Overview

This project is a backend-focused chat application where users can:

- Sign up
- Log in using JWT authentication
- View current logged-in user details
- View all available users
- Open a direct chat with another user
- Send and load messages
- Run the app locally, inside Docker, or on AWS

The project was initially built with basic login/session flow and later upgraded to **JWT-based stateless authentication**.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Backend programming language |
| Spring Boot | Main backend framework |
| Spring Security | Authentication and API protection |
| JWT | Stateless login authentication |
| MySQL | Database |
| Spring Data JPA | Database operations |
| HTML/CSS/JavaScript | Frontend UI |
| Docker | Containerized deployment |
| AWS EC2 | Cloud deployment |
| Maven | Build tool |

---

## Current Features

- User signup
- JWT-based login
- Token stored in browser `localStorage`
- Protected REST APIs using `Authorization: Bearer <token>`
- Current user profile loading
- User list loading
- Direct chat creation/opening
- Message sending
- Message loading by conversation
- Docker support
- AWS deployment-ready configuration

---

## JWT Authentication Flow

```text
login.html
→ login.js
→ POST /api/auth/login
→ AuthController
→ JwtUtil generates token
→ LoginResponse returns token
→ token stored in localStorage
→ loadUser.js sends Authorization header
→ JwtAuthenticationFilter validates token
→ SecurityContextHolder stores current user
→ Protected APIs use current user
