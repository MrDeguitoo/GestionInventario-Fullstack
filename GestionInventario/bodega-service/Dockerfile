FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY . .
RUN mvn clean package -pl bodega-service -am -DskipTests

FROM eclipse-temurin:21-jre-jammy
COPY --from=build /app/bodega-service/target/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app.jar"]