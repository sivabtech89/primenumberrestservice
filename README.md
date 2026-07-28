# primenumber restservice guide

This RESTful service calculates and returns all the prime numbers up to and including a number provided using spring boot.

Have used the apache commons lib for persisting in-memory the limit response for fast retrieval instead of going to the actual business logic. Can be replaced by Redis - An in-memory cache is a high-speed data storage layer

#### Swagger , API Docs, management endpoints details:

Swagger - http://localhost:8080/swagger-ui/index.html

API Docs - http://localhost:8080/v3/api-docs

Service Info - http://localhost:8080/actuator/info

Service Health - http://localhost:8080/actuator/health

#### Endpoint Details:

This service expose a GET endpoint /primes/{number} and will return a JSON content including a number provided. 

**Tech Stack Used:**

* Core Java - JDK 21
* Spring Boot - 4.1.0
* Lombok - 1.18.46
* Karate API Test Framework - 1.5.2
* Commons collections4 - 4.5.0
* JaCoCo Maven - 0.8.11

**Junit & Karate Integration Test Evidence can be found under testevidences folder**
