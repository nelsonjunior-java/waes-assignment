FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /app
WORKDIR /app
COPY build/libs/gateway.jar /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/gateway.jar"]
