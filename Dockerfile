FROM adoptopenjdk/openjdk11:ubi
ADD target/am-service.jar /app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker","/app.jar"]