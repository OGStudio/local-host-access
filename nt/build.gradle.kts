plugins {
    application
    kotlin("multiplatform") version "2.0.0"
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
                //implementation("io.ktor:ktor-client-core:$ktor_version")
                //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$json_version")
            }
        }
        val nativeMain by getting {
            dependencies {
                //implementation("io.ktor:ktor-client-curl:$ktor_version")
            }
        }
    }
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.BIN
}
