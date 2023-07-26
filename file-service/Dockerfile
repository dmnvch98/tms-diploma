FROM openjdk:17-jdk

WORKDIR /app

COPY target/file-service-0.0.1-SNAPSHOT.jar file-service.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://diploma-postgres-test:5432/diploma-test
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

ENV SPRING_PROFILES_ACTIVE=production
ENV LOCALSTACK_HOST=localstack

EXPOSE 7091 5007

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5007 -jar file-service.jar