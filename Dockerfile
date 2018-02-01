FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/servicebus-0.0.1-SNAPSHOT.jar app.jar
COPY /json json
COPY /jsonUsers jsonUsers
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
