FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src
RUN mvn clean package -DskipTests
EXPOSE 8081
CMD ["java", "-jar", "/app/target/erp-backend-1.0-SNAPSHOT.jar"]
