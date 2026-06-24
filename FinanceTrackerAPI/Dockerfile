# -------- Build stage --------
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build

COPY pom.xml .
RUN mvn -q -B -e -C -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests package


# -------- Runtime stage --------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /build/target/finance-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75.0","-jar","app.jar"]
