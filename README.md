# Miras Suleimenov — Student API

**Student:** Miras Suleimenov  
**Group:** IT2-2313  
**ID:** 37233  

## Description

Spring Boot CRUD REST API for managing students, containerised with Docker and automated via Jenkins CI/CD.  
Created as part of the DevOps midterm task.

## Endpoints

| Method | URL                    | Description           |
|--------|------------------------|-----------------------|
| GET    | /api/students          | Get all students      |
| GET    | /api/students/{id}     | Get student by ID     |
| POST   | /api/students          | Create a student      |
| PUT    | /api/students/{id}     | Update a student      |
| DELETE | /api/students/{id}     | Delete a student      |

## Quick Start

### Run with Docker Compose

```bash
docker-compose up --build
```

Application will be available at `http://localhost:8080`.

### Build locally (requires JDK 17 + Gradle)

```bash
gradle wrapper
./gradlew bootJar
java -jar build/libs/*.jar
```

## API Examples (curl)

```bash
# Create student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Miras Suleimenov","studentGroup":"IT2-2313","studentId":37233}'

# Get all
curl http://localhost:8080/api/students

# Get by ID
curl http://localhost:8080/api/students/1

# Update
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Miras Suleimenov","studentGroup":"IT2-2313","studentId":37233}'

# Delete
curl -X DELETE http://localhost:8080/api/students/1
```

## Project Structure

```
miras-suleimenov-student-api/
├── scripts/   Miras_Suleimenov_system_report.sh
├── logs/      Miras_Suleimenov_system_report.log
├── backups/   README backups
├── src/       Spring Boot source code
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
├── build.gradle
└── README.md
```

## Change Log

- Initial commit: Miras Suleimenov Student API
- feat: implement Student CRUD operations
- feat: add Docker and Docker Compose configuration
