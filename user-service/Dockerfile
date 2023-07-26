FROM openjdk:17-jdk

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://diploma-postgres-test:5432/diploma-test
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

ENV SPRING_PROFILES_ACTIVE=production
ENV CONV_SVC_HOST=conversation-service

EXPOSE 7090 5005

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar user-service.jar
