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

# Copy the webapp-runner that was downloaded by Maven
COPY --from=build /app/target/dependency/webapp-runner.jar ./webapp-runner.jar

# Copy the .war file from the build stage
COPY --from=build /app/target/CropConnect.war ./CropConnect.war

# Expose port (documentation only - Render uses dynamic PORT)
EXPOSE 8080

# Run the application with dynamic port support
# ${PORT:-8080} means: use $PORT if set, otherwise use 8080
CMD java -jar webapp-runner.jar --port ${PORT:-8080} CropConnect.war