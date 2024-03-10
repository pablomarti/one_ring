# One Ring

Beam Pipeline built with Kotlin that runs with the Flink Runner. It consumes from a Kafka topic and writes the messages to a text file.

For running this pipeline locally you will need:
- Start the Kafka brokers and produce messages
- Start Flink
- Build and run the Beam pipeline

Note: Flink works only with Java 11 or 8, so this project works with Java 11, and because of that I decided to go with Kotlin to get the nice features that
we are missing from newer Java versions. For Dataflow this could work with Java 19.

### Run Kafka

You can clone the Kafka mirror from https://github.com/apache/kafka and [follow these steps](https://kafka.apache.org/quickstart) to start the services and produce the messages. Here we work
with the topic: `nazgul`. If you clone from the repo you will need to build the project: `./gradlew jar -PscalaVersion=2.13.12./gradlew jar -PscalaVersion=2.13.12`.

### Run Flink

Similar to Kafka you can clone the repo from https://github.com/apache/flink or download the tar from https://flink.apache.org/downloads/. I think the best is to work with
Flink 1.16.3 for compatibility with the Beam connector, you can start the service with: `./bin/start-cluster.sh`. And you can stop it with `./bin/stop-cluster.sh`.

### Run the pipeline

Build the fat jar:

```
./gradlew shadowJar
```

#### Run the pipeline using the DirectRunner

```
./gradlew run
```

#### Run the pipeline using the FlinkRunner

In the Flink directory run the jar:

```
./bin/flink run [path to the jar]/app.jar-0.1.0-all.jar --runner=FlinkRunner
```

You can also go to the Flink Dashboard and upload the jar at: Submit New Job. It may take a few minutes to run the pipeline at Flink.

<img width="1896" alt="Screenshot 2024-03-10 at 03 38 26" src="https://github.com/pablomarti/one_ring/assets/672530/cf2968e1-a625-46b7-a090-bd4dafaeb796">

