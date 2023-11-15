# Use the official OpenJDK base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/movie-ratings-data-service-0.0.1-SNAPSHOT.jar /app/movie-ratings-data-service-0.0.1-SNAPSHOT.jar

# Expose the port that your application will run on
EXPOSE 11001

# Specify the command to run on container startup
CMD ["java", "-jar", "/app/movie-ratings-data-service-0.0.1-SNAPSHOT.jar" ]
