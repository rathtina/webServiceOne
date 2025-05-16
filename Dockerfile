# -------- Build Stage --------
FROM maven:3.8.5-openjdk-17 AS builder
COPY . .
RUN mvn clean package -DskipTests

# -------- Runtime Stage --------
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=builder /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]

