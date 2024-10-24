val json_version: String by project
val ktor_version: String by project

plugins {
    // Support Kotlin in JVM.
    alias(libs.plugins.jvm)
    // Support for building a CLI application in Java.
    application
    // JSON parsing.
    kotlin("plugin.serialization") version "2.0.20"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.opengamestudio.JVMAppKt"
}

// Make `jar` gradle task produce runnable jar.
tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.opengamestudio.JVMAppKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-cio:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$json_version")
}
