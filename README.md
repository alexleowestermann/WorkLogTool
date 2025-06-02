# Work Log

Full-stack work log app using Spring Boot + HTML/JavaScript.

---

## Requirements

- Java 17+
- Maven
- MySQL running on port 3306

---

## Running the application locally

### Backend

```bash
cd work-log
./mvnw spring-boot:run
```

Runs at: **http://localhost:8080**

---

## Features

- Create, view, and delete work log entries.
- Entries include title, description, date, and tags.
- Basic frontend with add/view functionality.
- MySQL database.
- CORS enabled (for API access if needed).

---

## API Endpoints

| Method | Endpoint         | Description                     |
|--------|------------------|---------------------------------|
| GET    | /api/logs        | Get all work log entries        |
| POST   | /api/logs        | Create a new work log entry     |
| DELETE | /api/logs/{id}   | Delete an entry by ID           |

---

## Running tests

```bash
cd work-log
./mvnw test
```

Tests cover model, service, and controller logic using JUnit.

---

## Author

Alexander Westermann