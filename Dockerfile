FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/restful-jpa-0.0.1-SNAPSHOT.jar gcv.jar
ENTRYPOINT ["java","-jar","/gcv.jar"]

#FROM openjdk:17-alpine
#ARG JAR_FILE=build/buildSpring-*.jar
#ADD ${JAR_FILE} GCV.jar
#ENTRYPOINT ["java","-jar","/GCV.jar"]