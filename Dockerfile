FROM openjdk:19-jdk-alpine
ARG JAR_FILE=target/covid-statistics-rest-jar-with-dependencies.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]