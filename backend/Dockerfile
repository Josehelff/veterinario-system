FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/veterinario-system-1.0.0.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Xmx512m -Xms256m"

CMD ["java", "-jar", "app.jar"]
