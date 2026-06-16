FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY . .
RUN mvn clean package -pl notificacion-service -am -DskipTests

FROM eclipse-temurin:21-jre-jammy
COPY --from=build /app/notificacion-service/target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app.jar"]