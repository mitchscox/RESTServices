# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Install Maven
RUN apk add --no-cache maven

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Build the application
RUN mvn package

# Make port 8080 and 80 available to the world outside this container
EXPOSE 8080
EXPOSE 80

# Run the jar file (assumes jar file is in target directory after build)
CMD ["java", "-jar", "target/TestAutomationProject-1.0-SNAPSHOT.jar"]