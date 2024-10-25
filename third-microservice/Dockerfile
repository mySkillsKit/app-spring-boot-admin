FROM openjdk:17
VOLUME /tmp
EXPOSE 8083
ADD build/libs/third-microservice-0.0.1-SNAPSHOT.jar third-microservice.jar
ENTRYPOINT ["java", "-jar", "/third-microservice.jar"]