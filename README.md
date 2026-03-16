Book-my-ticket app

# BookMyTicket - End-to-End CI/CD Pipeline & Application

A Spring Boot 4.0.3 application (Java 21) for managing seat booking in a ticketing system.

---

# Overview
This project demonstrates:
- **Backend APIs** with Spring Boot
- **CI/CD pipeline** from GitHub
---

# Features
- REST APIs for seat management (create, book, list)
- Java 21 + Spring Boot 4.0.3
- JPA/Hibernate integration

  # End poits with payload
  # seat creation
  POST http://localhost:8080/api/seat/100
  # for book seats
  POST http://localhost:8080/api/seat/book
  Content-Type: application/json
{
  "seatIds": [1, 4],
  "userName": "name"
}

# for get All seats
GET http://localhost:8080/api/seat/getAllSeats
# for get booked seats

GET http://localhost:8080/api/seat/getBookedSeats




  

