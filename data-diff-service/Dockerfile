FROM gradle:6-jdk11-alpine

COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --stacktrace

# FROM adoptopenjdk/openjdk11:alpine
FROM eclipse-temurin:11-alpine

RUN mkdir /app
WORKDIR /app
COPY --from=0 app/build/libs/*.jar /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/data-diff-service.jar"]
