# jpop-library-service

Library Service for JPoP Program

## Implementation:

- Uses spring-web, spring-data-jpa and in memory H2 database. Available at context root /h2-console
- Uses lombok for generating boiler plate code
- The service context root is /library-api
- Default server port is 8083
- Added actuator endpoint for health http://localhost:8083/library/actuator/health
- Added flyway for easy database/schema migration

## Features:

1. Exposes REST APIs for verifying user, and their interaction with books
2. Exposes Swagger UI under the application root context /library-api
3. Swagger api docs are available under /library-api/docs
4. Uses Cloud Config Server to fetch configurations
5. Uses Eureka Discovery Server to register the instances