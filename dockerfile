FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
# копируем файлы с зависимостями и pom.xml, чтобы ускорить процесс сборки
COPY pom.xml .
COPY src ./src

# собираем проект Maven
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
# копируем собранный JAR файл из предыдущего этапа
COPY --from=build /app/target/ton_blockchain-0.0.1-SNAPSHOT.jar ./ton_blockchain.jar
COPY --from=build /app/src/main/resources/liquibase/db_changelog_master.yaml ./db_changelog_master.yaml
# устанавливаем команду запуска для выполнения JAR файла
CMD ["java", "-jar", "ton_blockchain.jar"]