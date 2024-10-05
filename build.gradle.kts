import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotest.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.detekt)
}

group = "io.github.MrNuggelz"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

kotlin {
    explicitApi()
    jvmToolchain(21)

    jvm()
    js {
        nodejs()
    }

    /* Enable when there is support in ktor-client
    wasmJs {
        nodejs()
         browser()
    }
     */

    /* Enable when there is support in ktor-client
    wasmWasi {
        nodejs()
    }
     */

    nativeTargets()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.cryptohash)
            implementation(libs.ktor.client.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines)
        }
        commonTest.dependencies {
            implementation(libs.kotest.frameworkEngine)
            implementation(libs.kotest.assertions)
            implementation(libs.ktor.client.mock)
        }
        jvmTest.dependencies {
            implementation(libs.slf4jSimple)
            implementation(libs.kotest.junit)
        }
    }
}

detekt {
    source.setFrom(".")
    buildUponDefaultConfig = true
    dependencies {
        detektPlugins(libs.plugins.detektFormatting.map { "${it.pluginId}:${it.version}" })
    }
}

tasks.register<Detekt>("detektFix") {
    setSource(".")
    buildUponDefaultConfig = true
    dependencies {
        detektPlugins(libs.plugins.detektFormatting.map { "${it.pluginId}:${it.version}" })
    }
    ignoreFailures = true
    autoCorrect = true
}

private fun KotlinMultiplatformExtension.nativeTargets() {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosX64()
    macosArm64()

    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()

    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()

    mingwX64()

    linuxX64()
    linuxArm64()
}
