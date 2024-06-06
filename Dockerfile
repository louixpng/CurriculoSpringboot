FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} curriculo-aos-0.0.1-SNAPSHOT.jar.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/curriculo-aos-0.0.1-SNAPSHOT.jar"]