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
# Use the smaller 'alpine' image (Cursor's correct suggestion)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# --- THIS IS THE CORRECTED SECTION ---
# Use Alpine's package manager 'apk' to download the correct 'jakarta10' runner
RUN apk add --no-cache curl && \
    curl -L -o webapp-runner.jar "https://repo1.maven.org/maven2/com/github/jsimone/webapp-runner-jakarta10/10.1.25.0/webapp-runner-jakarta10-10.1.25.0.jar" && \
    apk del curl
# --- END SECTION ---

# Copy the .war file from the build stage
COPY --from=build /app/target/CropConnect.war ./CropConnect.war

# Use the dynamic PORT variable (Cursor's correct suggestion)
CMD java -jar webapp-runner.jar --port ${PORT:-8080} CropConnect.war