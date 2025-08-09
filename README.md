# Todo Backend API

This is a simple Spring Boot application that provides a RESTful API for managing to-do tasks. It allows clients to create, list, and delete tasks with basic status management.

## Features

- Create a new task with a title and optional status
- List all tasks
- Delete a task by ID
- Immutable DTOs using Java 16+ records
- Validation with Jakarta Bean Validation

## Technology Stack

- Java 17+
- Spring Boot 3.x
- Maven 3.x
- Jackson for JSON serialization/deserialization
- JUnit 5 for unit testing

## Getting Started

### Prerequisites

- Java 17 or newer
- Maven 3.6 or newer

### Clone the repository

```bash
git clone https://github.com/andre/todo-backend-java.git
cd todo-backend-java
```

### Running the application

You can run the application with Maven:

```bash
./mvnw spring-boot:run
```

Or build a fat JAR and run it:

```bash
./mvnw clean package
java -jar target/todo-backend-java-0.0.1-SNAPSHOT.jar
```

The service will start on `http://localhost:8080` by default.

### Running tests

```bash
./mvnw test
```

## Configuration

Configuration is located in `src/main/resources/application.yml` or `application.properties`. You can customize server port, data source, etc.

## API Endpoints

### List all tasks

- **URL:** `GET /api/tasks`
- **Response:** `200 OK` with an array of `TaskResponse` objects

Example response:
```json
[
  {
    "id": 1,
    "title": "Buy groceries",
    "status": "TODO",
    "createdAt": "2025-08-09T12:34:56",
    "updatedAt": "2025-08-09T12:34:56"
  }
]
```

### Create a new task

- **URL:** `POST /api/tasks`
- **Content-Type:** `application/json`
- **Request Body:** `CreateTaskRequest` JSON

```json
{
  "title": "Learn Spring Boot",
  "status": "IN_PROGRESS" // Optional, defaults to TODO
}
```

- **Response:** `201 Created` with `Location` header and `TaskResponse` body

### Delete a task

- **URL:** `DELETE /api/tasks/{id}`
- **Response:** `204 No Content` on success, `404 Not Found` if the ID does not exist

## Data Model

- **Task**: JPA entity representing a to-do item
- **TaskStatus**: Enum of `TODO`, `IN_PROGRESS`, `DONE`

## DTOs

- **CreateTaskRequest**: Request DTO for creating tasks (record)
- **TaskResponse**: Response DTO for tasks (record)

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
