# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the .war
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final, small image (using jammy for apt-get compatibility)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# --- FINAL CORRECTED DOWNLOAD COMMAND ---
# 1. Update and install curl using apt-get
# 2. Curl the correct, direct Maven Central URL (-L follows redirects)
# 3. Clean up
RUN apt-get update && apt-get install -y curl && \
    curl -L -o webapp-runner.jar "https://repo1.maven.org/maven2/com/github/jsimone/webapp-runner-jakarta10/10.1.25.0/webapp-runner-jakarta10-10.1.25.0.jar" && \
    apt-get purge -y curl && apt-get autoremove -y && rm -rf /var/lib/apt/lists/*
# --- END FINAL CORRECTED DOWNLOAD COMMAND ---

# Copy just the .war file from the build stage
COPY --from=build /app/target/CropConnect.war ./CropConnect.war

# Expose the default HTTP port
EXPOSE 8080

# Set the start command to use the dynamic PORT variable provided by Render
CMD java -jar webapp-runner.jar --port ${PORT:-8080} CropConnect.war