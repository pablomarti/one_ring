package com.onering

import com.onering.pipelines.*

fun main(args: Array<String>) {
    val argMap = args.mapNotNull {
        val split = it.split("=")
        if (split.size == 2) split[0] to split[1] else null
    }.toMap()

    println("Running pipeline: $argMap - ${argMap["--pipelineName"]}")

    val pipelineName = argMap["--pipelineName"] ?: throw IllegalArgumentException("Pipeline required")

    val pipeline: Pipeline = when (pipelineName) {
        "KafkaToText" -> KafkaToText()
        else -> {
            throw IllegalArgumentException("Unknown pipeline: $pipelineName")
        }
    }

    pipeline.run(args)
}
