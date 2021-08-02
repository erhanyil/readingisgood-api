FROM openjdk:11-jre-slim
COPY target/readingisgood-0.0.1-SNAPSHOT.jar readingisgood.jar
ENTRYPOINT ["java","-jar","/readingisgood.jar"]