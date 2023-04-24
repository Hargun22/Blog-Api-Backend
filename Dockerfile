FROM eclipse-temurin:17

WORKDIR /app

COPY ./target/blog-api-0.0.1-SNAPSHOT.jar /app/blog-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/blog-api-0.0.1-SNAPSHOT.jar"]