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
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# --- THIS IS THE CORRECTED SECTION ---
# Download the correct webapp-runner manually, following redirects (-L)
RUN apt-get update && apt-get install -y curl && \
    curl -L -o webapp-runner.jar "https://search.maven.org/remotecontent?filepath=com/github/jsimone/webapp-runner-jakarta10/10.1.25.0/webapp-runner-jakarta10-10.1.25.0.jar" && \
    apt-get purge -y curl && apt-get autoremove -y && rm -rf /var/lib/apt/lists/*
# --- END SECTION ---

# Copy just the .war file from the build stage
COPY --from=build /app/target/CropConnect.war ./CropConnect.war

# Tell Render what port our app will listen on
EXPOSE 10000

# Set the start command
CMD ["java", "-jar", "webapp-runner.jar", "--port", "10000", "CropConnect.war"]