FROM openjdk:17
VOLUME /tmp
EXPOSE 8060
ADD build/libs/spring-boot-admin-server-0.0.1-SNAPSHOT.jar admin-server.jar
ENTRYPOINT ["java", "-jar", "/admin-server.jar"]