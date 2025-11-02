# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the .war
COPY src ./src
RUN mvn clean package

# Stage 2: Create the final, small image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the webapp-runner (from the plugin) and the .war file
COPY --from=build /app/target/dependency/webapp-runner.jar ./webapp-runner.jar
COPY --from=build /app/target/CropConnect.war ./CropConnect.war

# Tell Render what port our app will listen on
EXPOSE 10000

# Set the start command
CMD ["java", "-jar", "webapp-runner.jar", "--port", "10000", "CropConnect.war"]