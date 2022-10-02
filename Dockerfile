FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} crudEcuador-1.0.jar
ENTRYPOINT ["java","-jar","/crudEcuador-1.0.jar"]