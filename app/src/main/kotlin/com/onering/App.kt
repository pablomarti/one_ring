package com.onering

import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.kafka.KafkaIO
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.PipelineOptions
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.beam.sdk.transforms.Values
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.joda.time.Duration
import org.apache.beam.sdk.transforms.windowing.FixedWindows
import org.apache.beam.sdk.transforms.windowing.Window

fun main(args: Array<String>) {
    val options = PipelineOptionsFactory.fromArgs(*args).withValidation().`as`(PipelineOptions::class.java)
    val pipeline = Pipeline.create(options)

    val kafkaSource = KafkaIO.read<Long, String>()
        .withBootstrapServers("localhost:9092")
        .withTopic("nazgul")
        .withKeyDeserializer(LongDeserializer::class.java)
        .withValueDeserializer(StringDeserializer::class.java)
        .withConsumerConfigUpdates(mapOf(
            "auto.offset.reset" to "earliest"
        ))
        .withoutMetadata()

    pipeline
        .apply("ReadFromKafka", kafkaSource)
        .apply("ExtractValues", Values.create<String>())
        .apply("Window",
            Window.into<String>(FixedWindows.of(Duration(500)))
        )
        .apply("WriteToFile", TextIO.write().to("output/messages")
            .withWindowedWrites()
            .withNumShards(1)
            .withSuffix(".txt")
        )

    pipeline.run().waitUntilFinish()
}
