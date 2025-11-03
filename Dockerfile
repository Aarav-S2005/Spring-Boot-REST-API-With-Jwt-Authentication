FROM openjdk:21-jdk

WORKDIR /app

COPY target/SpringBootRestApiWithJwtAuth-0.0.1-SNAPSHOT.jar /app/SpringBootRestApiWithJwtAuth.jar

EXPOSE 8080

CMD [ "java", "-jar", "SpringBootRestApiWithJwtAuth.jar" ]