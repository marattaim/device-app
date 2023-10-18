FROM maven:3.9.4-eclipse-temurin-17 as build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package

FROM bellsoft/liberica-runtime-container:jre-17-slim-musl

WORKDIR /app

COPY --from=build target/test-device-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]
