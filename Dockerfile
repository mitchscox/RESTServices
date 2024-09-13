# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Install Maven
RUN apk add --no-cache maven

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml first and download dependencies
COPY pom.xml /app
RUN mvn dependency:go-offline -B

# Copy the entire project
COPY . /app

# Build the application
RUN mvn clean package

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file (adjust the target path as needed)
CMD ["java", "-jar", "target/TestAutomationProject-1.0-SNAPSHOT.jar"]
