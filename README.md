# mvc spring boot web app

- Implement web page having registration page containing customer info (Username, Password, Email, Phone, First name and
  Last name).
- Implement web page having login page containing Username and Password which redirects to Welcome page.
- Use spring boot in your implementation.
- Use embedded DB in your application, DB: containing Customer info.
- Do validations.
- Do junit testing.
- Use Maven to build your code.
- Try to use design pattern in the code, code style is considered in the task.
- Try using security modules on spring for the password, encrypt the password.
- Delivery package is Docker file, runnable jar and the source code.

## Prerequisites

- Java 17 or later
- Maven (optional)
- Docker (optional)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/eslamSaeed12/spring-mvc-task
    ```

2. Navigate to the project directory:

    ```bash
    cd spring-mvc-task
    ```

3. Build the project using Maven:

    ```bash
    ./mvnw clean install
    ```

## Run Using Docker

1. Navigate to the project directory:

    ```bash
    cd spring-mvc-task
    ```
2. Build docker image
    ```bash
    docker build -t springmvcapp .
    ```
3. Run Docker Image
    ```bash
    docker run -p 8080:8080 springmvcapp
    ```

## Run Locally

1. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

2. Open your web browser and visit [http://localhost:8080](http://localhost:8080) to access the application.

## Run Tests

Execute the following command to run tests:

```bash
./mvnw test
