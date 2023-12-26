FROM eclipse-temurin:17

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/webapp-0.0.1-SNAPSHOT.jar"]