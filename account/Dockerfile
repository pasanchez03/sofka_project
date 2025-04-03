FROM gradle:8.12.1-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle clean build -x test --refresh-dependencies

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/account*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
