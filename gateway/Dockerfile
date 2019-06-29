FROM openjdk:8-jdk-alpine
ADD target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]
EXPOSE 9999

