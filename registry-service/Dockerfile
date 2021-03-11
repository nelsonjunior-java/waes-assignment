FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /app
WORKDIR /app
COPY build/libs/registry-service.jar /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/registry-service.jar", "--spring.profiles.active=docker"]
EXPOSE 8761