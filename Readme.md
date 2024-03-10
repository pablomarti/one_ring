# One Ring

Beam Pipeline that runs with the Flink Runner. It consumes from a Kafka topic and writes the messages to a text file.

### Run Kafka

You can clone the Kafka mirror from https://github.com/apache/kafka and follow these steps to start the services and produce the messages. Here we work
with the topic: `nazgul`. If you clone from the repo you will need to build the project: `./gradlew jar -PscalaVersion=2.13.12./gradlew jar -PscalaVersion=2.13.12`.

### Run Flink

Similar to Kafka you can clone the repo from https://github.com/apache/flink or download the tar from https://flink.apache.org/downloads/. I think the best is to work with
Flink 1.16.3 for compatibility with the Beam connector, you can start the service with: `./bin/start-cluster.sh`.

### Run the pipeline

Build the fat jar:

```
./gradlew shadowJar
```

In the Flink directory run the jar:

```
./bin/flink run [path to the jar]/app.jar-0.1.0-all.jar
```
