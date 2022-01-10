FROM openjdk:17
ADD target/LeaderElectionMaven-1.0-SNAPSHOT-jar-with-dependencies.jar LeaderElectionMaven-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar","LeaderElectionMaven-1.0-SNAPSHOT-jar-with-dependencies"]
EXPOSE 8080