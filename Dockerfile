# FYI : for prebuild JAR via mvn clean install (my normal approach). then use docker-compose.yml to build the image and run the container

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the pre-built JAR file
COPY target/*.jar app.jar

# Environment variables
ENV PORT=8080

# Expose the port
EXPOSE ${PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]