# Dockerfile for cloud-hashing-service
FROM openjdk:8
ADD target/cloud-hashing-service-0.0.1-SNAPSHOT.jar /opt/micro-services/
WORKDIR /opt/micro-services/
EXPOSE 8205
ENTRYPOINT ["java", "-jar", "cloud-hashing-service-0.0.1-SNAPSHOT.jar"]
