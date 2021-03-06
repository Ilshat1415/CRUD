FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
COPY ./ /usr/local/app
WORKDIR /usr/local/app
RUN mvn clean package

FROM openjdk:8u212-jre-alpine3.9
COPY --from=MAVEN_BUILD /usr/local/app/target/CRUD-0.0.1-SNAPSHOT.jar /CRUD.jar
EXPOSE 8081:8081
CMD ["java", "-jar", "CRUD.jar"]