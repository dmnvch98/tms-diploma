FROM openjdk:17-jdk

WORKDIR /app

COPY user-service/target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
COPY conversation-service/target/conversation-service-0.0.1-SNAPSHOT.jar conversation-service.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://diploma-postgres-test:5432/diploma-test
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

ENV SPRING_PROFILES_ACTIVE=production

EXPOSE 7090 5005 7093 5006

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "user-service.jar", "--spring.config.location=classpath:/application-test.yaml", "&", "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006", "-jar", "conversation-service.jar", "--spring.config.location=classpath:/application-test.yaml"]
