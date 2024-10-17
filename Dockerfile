# Use a Maven image with JDK 1.8 to build the application
FROM maven:3.6.3-jdk-8 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project files to the container
COPY src ./src

# Build the application (this will generate the JAR file)
RUN mvn clean package -DskipTests

# Use an OpenJDK 8 image to run the application
FROM openjdk:8-jdk-alpine

# Set the working directory for the runtime
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/Visualizimi-i-algoritmeve-0.0.1-SNAPSHOT.jar /app/Visualizimi-i-algoritmeve.jar

# Expose the port on which your Spring Boot app runs
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "Visualizimi-i-algoritmeve.jar"]
