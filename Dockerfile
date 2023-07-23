# Используйте официальный образ с JDK
FROM openjdk:17-jdk

# Установите рабочую директорию
WORKDIR /app

# Скопируйте JAR-файл микросервиса в образ
COPY user-service/target/user-service-0.0.1-SNAPSHOT.jar .

# Установите переменные окружения для подключения к базе данных
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://diploma-postgres-test:5432/diploma-test
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Установите переменную окружения для Spring Boot
ENV SPRING_PROFILES_ACTIVE=production

# Откройте порты, которые будут использоваться микросервисом
EXPOSE 7090 5005

# Запустите микросервис при запуске контейнера
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
