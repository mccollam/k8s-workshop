FROM openjdk:10-jdk-slim as builder

COPY . /usr/src/spring-boot-metrics
WORKDIR /usr/src/spring-boot-metrics

RUN ./gradlew clean assemble

FROM openjdk:10-jre-slim as adjective-service
EXPOSE 8080
COPY --from=builder /usr/src/spring-boot-metrics/adjective-service/build/libs/adjective-service-0.0.1-SNAPSHOT.jar /app/adjective-service.jar
WORKDIR /app
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar ./adjective-service.jar --management.metrics.export.signalfx.access-token=$SIGNALFX_TOKEN --server.port=8080

FROM openjdk:10-jre-slim as noun-service
EXPOSE 8080
COPY --from=builder /usr/src/spring-boot-metrics/noun-service/build/libs/noun-service-0.0.1-SNAPSHOT.jar /app/noun-service.jar
WORKDIR /app
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar ./noun-service.jar --management.metrics.export.signalfx.access-token=$SIGNALFX_TOKEN --server.port=8080

FROM openjdk:10-jre-slim as insult-service
EXPOSE 8080
COPY --from=builder /usr/src/spring-boot-metrics/insult-service/build/libs/insult-service-0.0.1-SNAPSHOT.jar /app/insult-service.jar
WORKDIR /app
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar ./insult-service.jar --management.metrics.export.signalfx.access-token=$SIGNALFX_TOKEN --server.port=8080 --adjective.host=$ADJECTIVE_HOST --noun.host=$NOUN_HOST

