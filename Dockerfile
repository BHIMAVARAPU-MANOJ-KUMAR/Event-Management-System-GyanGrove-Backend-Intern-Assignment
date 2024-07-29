# Use the official OpenJDK as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the host to the container
ADD ./target/eventsmanagement-0.0.1-SNAPSHOT.jar /app/eventsmanagement-0.0.1-SNAPSHOT.jar

# Expose the port on which the Spring Boot app listens
EXPOSE 8090

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "eventsmanagement-0.0.1-SNAPSHOT.jar"]