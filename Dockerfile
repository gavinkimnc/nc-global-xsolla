FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/otholla-0.0.1.jar otholla-0.0.1.jar
ENTRYPOINT ["java", "-jar", "otholla-0.0.1.jar"]