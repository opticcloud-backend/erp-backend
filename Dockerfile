FROM openjdk:21-jdk-slim
WORKDIR /app
COPY ./erp-backend-1.0-SNAPSHOT.jar /app/erp-backend-1.0-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/erp-backend-1.0-SNAPSHOT.jar"]
