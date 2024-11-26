# Learning Navigator Service

This service is a simplified Exam Registration API, part of a Learning Management System. It handles CRUD operations for
Students, Subjects, and Exams, while ensuring proper relationships and constraints between these entities.

## Features

#### 1. Student Management:

* Register new students with a unique Registration ID and name.
* Enroll students in subjects.
* Retrieve student details, including enrolled subjects and registered exams.

#### 2. Subject Management:

* Add new subjects with a unique Subject ID and name.
* Retrieve subject details, including the list of enrolled students.

#### 3. Exam Management:

* Schedule new exams with a unique Exam ID and associated subject.
* Allow students to register for exams only if they are enrolled in the subject.
* Retrieve exam details, including the list of registered students.

#### 4. Validation and Constraints:

* Prevent students from registering for exams unless they are enrolled in the corresponding subject.
* Maintain relationships between entities using foreign keys.

#### 5. Error Handling:

* Handle errors like invalid inputs, resource not found, or bad requests.
* Use appropriate HTTP codes such as 400 (Bad Request) and 404 (Not Found).

#### 6. Exception Handling:

* Centralized exception management using @ControllerAdvice and GlobalExceptionHandler for better maintainability.

#### 7. Basic Unit Testing:

* Mock and test core functionalities, such as:
    * Student registration and subject enrollment.
    * Exam registration validation.
    * Retrieval of entities like students, subjects, and exams.

## API Endpoints

| Method | Endpoint                                           | Description                                 |
|--------|----------------------------------------------------|---------------------------------------------|
| GET    | /learning-navigator-api/v1/student                 | Retrieve a list of all registered students. |
| GET    | /learning-navigator-api/v1/student/{id}            | Retrieve details of a specific student.     |
| POST   | /learning-navigator-api/v1/student                 | Register a new student.                     |
| PUT    | /learning-navigator-api/v1/student/{id}/addSubject | Add specific subject to student             | 
| PUT    | /learning-navigator-api/v1/student/{id}/addExam    | Add specific exam to student                |  
| DELETE | /learning-navigator-api/v1/student/{id}            | Deregister a specific student.              |
| GET    | /learning-navigator-api/v1/subject                 | Retrieve a list of all subjects.            |
| GET    | /learning-navigator-api/v1/subject/{id}            | Retrieve details of a specific subject.     |
| POST   | /learning-navigator-api/v1/subject                 | Add a new subject.                          |
| DELETE | /learning-navigator-api/v1/subject/{id             | Remove a specific subject.                  |
| GET    | /learning-navigator-api/v1/exam	                   | Retrieve a list of all exams.               |
| GET    | /learning-navigator-api/v1/exam/{id}               | Retrieve details of a specific exam.        |
| PUT    | /learning-navigator-api/v1/exam/{id}               | Update details of a specific exam.          |
| DELETE | /learning-navigator-api/v1/exam/{id}               | Remove a specific exam.                     |

## Pre-requisites
1. Java 21+
2. Gradle (or use the Gradle wrapper provided in the project)
3. SQL

## Installation
##### Clone the repository:
```bash
git clone https://github.com/divii98/learning-navigator.git
cd your-repo-name
```

## Running the Application
##### 1. Configure the Database:
Make sure to set up your database (e.g., MySQL) and update the database configurations in application.properties.
##### 2. Build and Run the Service:

Use Gradle to build and run the application
``
./gradlew bootRun
``

##### 3. Access the Application:

By default, the application will run at:
> http://localhost:8080


## Postman Collections

For testing the API endpoints, you can use the Postman collection provided in the link below:

[Learning Navigator API Collection](https://www.postman.com/apicollections-7830/apicollections/collection/7vkaxur/learning-navigator-apis?action=share&creator=28961073)
