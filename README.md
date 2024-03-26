# Note Taking Application

This is a simple RESTful API for a note-taking application. It allows users to perform basic CRUD (Create, Read, Update, Delete) operations on notes while utilizing Envers for audit trail functionality and H2 for in-memory storage.

## Accessing H2 Console

You can access the H2 console using the following URL:

[http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)

- **Username**: sa
- **Password**: (Leave empty or none)

## Accessing Swagger UI

You can access the Swagger UI for API documentation using the following URL:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## Features

- Create a new note
- Update an existing note
- Delete a note
- Retrieve notes with pagination
- Retrieve a specific note by its ID
- Audit trail functionality powered by Envers
- In-memory storage using H2 database

## Technologies Used

- Java
- Spring Boot
- Lombok
- Envers (for audit trail)
- H2 Database (for in-memory storage)
- Jakarta Persistence API (JPA)
- Jakarta Validation API
- Hibernate Envers

## Note Entity

The `Note` class represents a note entity in the application. It is annotated with JPA annotations for entity mapping and validation.

- **Annotations**:
  - `@Audited`: Enables auditing for this entity using Envers.
  - `@Entity`: Indicates that this class is an entity.
  - `@Table`: Specifies the table name for this entity in the database.
  - `@Builder`: Lombok annotation to generate builder methods for easy object creation.
  - `@NoArgsConstructor`: Lombok annotation to generate a no-argument constructor.
  - `@AllArgsConstructor`: Lombok annotation to generate a constructor with all arguments.
  - `@EntityListeners`: Specifies the entity listener class for auditing.

- **Fields**:
  - `id`: Unique identifier for the note. Generated automatically for new notes.
  - `title`: Title of the note.
  - `body`: Content of the note.
  - `createdBy`: Username of the user who created the note.
  - `createdAt`: Timestamp indicating when the note was created.
  - `updatedBy`: Username of the user who last updated the note.
  - `updatedAt`: Timestamp indicating when the note was last updated.

## Service Layer

### NoteService

The `NoteService` class provides methods for managing notes. It interacts with the `NoteRepository` to perform database operations.

- **Annotations**:
  - `@Service`: Indicates that this class is a service component in Spring.

- **Methods**:
  - `save`: Saves a note in the database.
  - `remove`: Deletes a note from the database by its ID.
  - `getNotes`: Retrieves a page of notes from the database with pagination.
  - `getNoteById`: Retrieves a note from the database by its ID.

## Repository

### NoteRepository

The `NoteRepository` interface extends `JpaRepository` and provides methods for CRUD operations on notes.

- **Methods**:
  - Inherited from `JpaRepository`: `save`, `delete`, `findById`, `findAll`, etc.

## Controllers

### NoteController

This controller handles incoming HTTP requests related to note operations. It interacts with the `NoteService` to perform business logic and data manipulation.

- **Annotations**:
  - `@Validated`: Indicates that this controller requires method-level validation.
  - `@RestController`: Indicates that this class is a controller and defines RESTful endpoints.
  - `@RequestMapping("/notes")`: Maps all HTTP operations under `/notes`.
  - `@RequiredArgsConstructor`: Lombok annotation to generate a constructor injecting dependencies.

- **Endpoints**:
  - `create`: Handles POST requests to create a new note.
  - `update`: Handles PUT requests to update an existing note.
  - `delete`: Handles DELETE requests to delete a note by its ID.
  - `getNotes`: Handles GET requests to retrieve notes with pagination.
  - `getNoteById`: Handles GET requests to retrieve a note by its ID.

## Notes

- Validation annotations (`@Validated`) are used to ensure that incoming data meets certain criteria before processing.
- Custom validation (`@NoteCheckRecordExist`) is applied to check the existence of a note before performing certain operations.
- Envers is utilized for audit trail functionality, allowing tracking of changes made to notes over time.
- H2 database is used for in-memory storage, providing a lightweight and easy-to-configure solution for development and testing.

This README provides a brief overview of the note-taking application and its functionalities, including the integration of Envers for audit trail capabilities, H2 for in-memory storage, and Swagger UI for API documentation. For detailed implementation and usage instructions, refer to the source code and documentation.