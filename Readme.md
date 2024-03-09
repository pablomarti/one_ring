# One Ring

### Run Kafka

You can clone the Kafka mirror from https://github.com/apache/kafka and follow these steps to start the services and produce the messages. Here we work
with the topic: `nazgul`. If you clone from the repo you will need to build the project: `./gradlew jar -PscalaVersion=2.13.12./gradlew jar -PscalaVersion=2.13.12`.

### Run Flink

Similar to Kafka you can clone the repo from https://github.com/apache/flink or download the tar from https://flink.apache.org/downloads/. I think the best is to work with
Flink 1.16.3 for compatibility with the Beam connector, you can start the service with: `./bin/start-cluster.sh`.
