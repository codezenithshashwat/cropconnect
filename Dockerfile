# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the .war
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final, small image
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Download Heroku's webapp-runner (Tomcat 10.1.x - supports Jakarta EE 10)
# This is the correct artifact that actually exists and supports jakarta.servlet
RUN apk add --no-cache curl && \
    curl -L -o webapp-runner.jar "https://repo1.maven.org/maven2/com/heroku/webapp-runner/10.1.33.0/webapp-runner-10.1.33.0.jar" && \
    apk del curl

# Copy the .war file from the build stage
COPY --from=build /app/target/CropConnect.war ./CropConnect.war

# Expose port (Render uses dynamic PORT)
EXPOSE 8080

# Use shell form to allow PORT environment variable substitution
# Render will set PORT automatically
CMD java -jar webapp-runner.jar --port ${PORT:-8080} CropConnect.war