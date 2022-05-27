FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD
COPY ./ /usr/local/app
WORKDIR /usr/local/app
RUN mvn -DskipTests clean package

FROM openjdk:8-jdk
COPY --from=MAVEN_BUILD /usr/local/app/target/CRUD-0.0.1-SNAPSHOT.jar /demo.jar
EXPOSE 8081:8081
CMD ["java", "-jar", "demo.jar"]