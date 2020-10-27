FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=out/artifacts/CovidStatisticsWeb_jar/CovidStatisticsWeb.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]