FROM openjdk:17
VOLUME /tmp
EXPOSE 8082
ADD build/libs/second-microservice-0.0.1-SNAPSHOT.jar second-microservice.jar
ENTRYPOINT ["java", "-jar", "/second-microservice.jar"]