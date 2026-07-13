FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml ./
COPY . ./
RUN mvn clean package -pl ms-api-gateway -am -DskipTests

FROM eclipse-temurin:21-jre-jammy
COPY --from=build /app/ms-api-gateway/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
