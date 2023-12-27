# mvc spring boot web app

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
