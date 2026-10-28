# Stage 1 — Build
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY settings.gradle build.gradle ./
COPY src ./src
RUN gradle bootJar --no-daemon

# Stage 2 — Runtime
FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="Miras Suleimenov <IT2-2313, ID:37233>"
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
