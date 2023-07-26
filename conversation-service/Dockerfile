FROM openjdk:17-jdk

WORKDIR /app

COPY /target/conversation-service-0.0.1-SNAPSHOT.jar conversation-service.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://diploma-postgres-test:5432/diploma-test
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

ENV SPRING_PROFILES_ACTIVE=production
ENV CONV_QUEUE_HOST=localstack
ENV USER_SVC_HOST=user-service

EXPOSE 7093 5006

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006 -jar conversation-service.jar
