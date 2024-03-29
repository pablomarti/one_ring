/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.6/userguide/building_java_projects.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */

val mainClassName = "com.onering.AppKt"
val BEAM_VERSION = "2.54.0"

group = "com.onering"
version = "0.1.0"

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
    // This dependency is used by the application.
    implementation(libs.guava)
    implementation("org.apache.beam:beam-sdks-java-core:$BEAM_VERSION")
    runtimeOnly("org.apache.beam:beam-runners-flink-1.16:$BEAM_VERSION")
    implementation("org.apache.beam:beam-sdks-java-io-kafka:$BEAM_VERSION")
    runtimeOnly("org.apache.beam:beam-runners-direct-java:$BEAM_VERSION")
    implementation("org.apache.kafka:kafka-clients:3.6.0")
    implementation(kotlin("reflect"))
    // implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("1.9.20")
        }
    }
}

tasks {
    shadowJar {
        archiveBaseName.set("app.jar")
        isZip64 = true
        mergeServiceFiles()
        manifest {
            attributes["Main-Class"] = "com.onering.AppPipelineKt"
        }
    }
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set(mainClassName)
}
