FROM gradle:8.12.1-jdk17 AS build
WORKDIR /workspace
COPY . .
RUN gradle bootJar --no-daemon && cp build/libs/*.jar /tmp/app.jar

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /tmp/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
