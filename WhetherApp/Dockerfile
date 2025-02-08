FROM openjdk:8-alpine

# Copy the JAR file into the container
#COPY --from=build /app/target/your-artifact.jar app.jar
COPY target/WhetherApp-0.0.1-SNAPSHOT.jar /usr/app/WhetherApp-0.0.1-SNAPSHOT.jar

# Set the working directory inside the container
WORKDIR /usr/app

# Expose the port the app runs on
EXPOSE 8080

# Run the application
#CMD ["java", "-jar", "Docker-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-jar", "WhetherApp-0.0.1-SNAPSHOT.jar"]



