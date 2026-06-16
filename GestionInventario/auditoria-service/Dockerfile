FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY . .
RUN mvn clean package -pl auditoria-service -am -DskipTests

FROM eclipse-temurin:21-jre-jammy
COPY --from=build /app/auditoria-service/target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]