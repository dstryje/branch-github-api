# Branch GitHub API
A Spring Boot REST API that retrieves public GitHub user profile information along with associated repositories and returns a structured response based on the Branch engineering take-home assignment requirements.

---

## Overview

This service accepts a GitHub username, retrieves user profile information from the GitHub public API, retrieves the user's repositories, and returns a consolidated response in a clean JSON format.

Example endpoint:

GET /api/v1/github/{username}

Example:

http://localhost:8080/api/v1/github/octocat

---

## Architecture and Design Decisions

The application follows a layered architecture to separate responsibilities and keep the codebase maintainable and easy to test.

```text
Controller → Service → Client → Mapper → DTO → Exception Handling
```

### Controller Layer

Responsible only for handling incoming HTTP requests and returning responses.

```text
GithubUserController
```

### Service Layer

Responsible for orchestration logic by coordinating API calls and delegating response transformation.

```text
GithubUserService
```

### Client Layer

Responsible for communicating with the external GitHub API using Spring Boot RestClient.

```text
GithubClient
GithubClientImpl
```

A dedicated RestClient configuration class centralizes GitHub API configuration and request headers.

```text
RestClientConfig
```

### Mapper Layer

Responsible for transforming GitHub API DTOs into the final API response contract.

```text
GithubMapper
```

This layer also handles formatting the GitHub account creation date into a more readable GMT format.

### DTO Separation

Separate DTOs were created for:

- GitHub external API responses
- Internal API response contract

This avoids coupling the API directly to GitHub’s response structure and improves maintainability.

### Exception Handling

Custom exceptions were implemented to provide centralized and consistent error handling.

```text
GithubUserNotFoundException
GithubApiException
GlobalExceptionHandler
ErrorResponse
```

Example error response:

```json
{
  "message": "User not found: invalid-user"
}
```

---

## Technology Stack

- Java 17
- Spring Boot
- Gradle
- JUnit 5
- Mockito
- Lombok
- Jackson

---

## Running the Application

The application can be run directly through IntelliJ IDEA.

### IntelliJ Setup

Create a Spring Boot run configuration targeting:

```text
BranchGithubApiApplication.java
```

Then start the application.

### Build with Gradle

A Gradle build configuration can be used to clean and build the project.

```bash
./gradlew clean build
```

---

## Running and Testing the API

Once the application is running locally, the API can be tested using Postman, a browser, or curl.

### Endpoint

```text
GET /api/v1/github/{username}
```

Example:

```text
http://localhost:8080/api/v1/github/octocat
```

### Testing with Postman

Create a GET request using:

```text
http://localhost:8080/api/v1/github/octocat
```

No request headers or authentication are required when calling the local API.

Internally, the application automatically attaches the required GitHub API headers when making outbound requests to GitHub.

---

## Example Successful Response

```json
{
  "user_name": "octocat",
  "display_name": "The Octocat",
  "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
  "geo_location": "San Francisco",
  "email": null,
  "url": "https://api.github.com/users/octocat",
  "created_at": "Tue, 25 Jan 2011 18:44:36 GMT",
  "repos": [
    {
      "name": "Hello-World",
      "url": "https://api.github.com/repos/octocat/Hello-World"
    }
  ]
}
```

---

## Example Error Response

If the GitHub user does not exist:

```json
{
  "message": "User not found: invalid-user"
}
```

---

## Testing Strategy

Unit and integration-style tests were created to validate each application layer.

### Mapper Test

Validates DTO transformation and response formatting.

```text
GithubMapperTest
```

### Service Test

Validates service orchestration and verifies interactions with dependencies using Mockito.

```text
GithubUserServiceTest
```

### Controller Test

Validates endpoint behavior, HTTP status codes, JSON serialization, and service delegation using MockMvc.

```text
GithubUserControllerTest
```

---

## Future Improvements

Possible improvements if expanding the application further:

- Response caching for repeated GitHub lookups
- API rate limit handling
- Retry logic for transient GitHub API failures
- Additional integration testing

Caching was intentionally not implemented in order to keep the solution aligned with the scope of the assignment.

---

## Author

Developed by **Daniel Stryjewski**.

The goal of this project was to create a simple and maintainable API solution while following clean architecture principles and keeping the implementation aligned with the scope of the assignment.
GitHub: https://github.com/dstryje