# Food Delivery Platform – Backend

## Overview
Backend service for a food-delivery platform supporting 3 user roles: Customer, Restaurant, Delivery.  
Built with **Spring Boot**, **Spring Data JPA**, and **MySQL**, exposing secure RESTful APIs with JWT authentication and authorization.

## Features
- Role-based **authentication & authorization** (JWT)
- CRUD operations for Users, Restaurants, Orders, Menu Items
- Business logic for order flow and role restrictions
- Layered architecture: Controller → Service → Repository
- Exception handling & validation

## Tech Stack
- Java 17
- Spring Boot 2.6.3
- Spring Data JPA (Hibernate)
- MySQL 8+
- JWT (JJWT library)

## Getting Started

### Setup
1. Create MySQL database `food_delivery_db`
2. Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/food_delivery_db
   spring.datasource.username=root
   spring.datasource.password=your_password
