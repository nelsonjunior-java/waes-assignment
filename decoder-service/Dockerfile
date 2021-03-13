FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /app
WORKDIR /app
COPY build/libs/decoder-service.jar /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/decoder-service.jar"]
