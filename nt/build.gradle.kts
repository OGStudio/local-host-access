val json_version: String by project
val ktor_version: String by project

val hostArch = System.getProperty("os.arch")
val hostOS = System.getProperty("os.name")

plugins {
    kotlin("multiplatform") version "2.0.20"
    // JSON parsing.
    kotlin("plugin.serialization") version "2.0.20"
}

repositories {
    mavenCentral()
}

kotlin {
    val nativeTarget = when {
        hostOS == "Mac OS X" && hostArch == "x86_64" -> macosX64("native")
        hostOS == "Mac OS X" && hostArch == "aarch64" -> macosArm64("native")
        hostOS == "Linux" -> linuxX64("native")
        hostOS == "Windows" && hostArch == "x86_64" -> mingwX64("native")
        else -> throw GradleException("Host OS+Arch is not yet supported")
    }
    nativeTarget.apply {
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
