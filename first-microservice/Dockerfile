FROM openjdk:17
VOLUME /tmp
EXPOSE 8081
ADD build/libs/first-microservice-0.0.1-SNAPSHOT.jar first-microservice.jar
ENTRYPOINT ["java", "-jar", "/first-microservice.jar"]