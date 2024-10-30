FROM openjdk:17
WORKDIR /app
COPY target/blogging-application-springboot.jar /app/blogging-application-springboot.jar
ENTRYPOINT ["java", "-jar", "/app/blogging-application-springboot.jar"]
EXPOSE 8080
