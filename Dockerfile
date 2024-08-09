FROM amazoncorretto:17-alpine-jdk
ARG JAR_FILE="build/libs/assessment-service-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
