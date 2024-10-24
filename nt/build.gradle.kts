val json_version: String by project
val ktor_version: String by project

plugins {
    kotlin("multiplatform") version "2.0.0"
    // JSON parsing.
    kotlin("plugin.serialization") version "2.0.20"
}

repositories {
    mavenCentral()
}

kotlin {
    macosX64("native") {
        binaries {
            executable()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktor_version")
                implementation("io.ktor:ktor-server-cio:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$json_version")
            }
        }
    }
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.BIN
}
