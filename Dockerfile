FROM maven:3.8.4-openjdk-17
COPY . /app
WORKDIR /app
RUN mvn clean compile package
ENTRYPOINT ["java", "-jar", "target/LeaderElectionMaven-1.0-SNAPSHOT-jar-with-dependencies.jar"]
