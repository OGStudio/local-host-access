plugins {
    // Support CLI app for JVM.
    application
    // Support several targets.
    kotlin("multiplatform") version "2.0.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

kotlin {
    jvm()
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.opengamestudio.AppKt"
}

// Make `jar` gradle task produce runnable jar.
tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.opengamestudio.AppKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
