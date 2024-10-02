import io.gitlab.arturbosch.detekt.Detekt

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
        browser()
        binaries.library()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.cryptohash)
            implementation(libs.ktor.client.json)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotest.frameworkEngine)
            implementation(libs.kotest.assertions)
            implementation(libs.ktor.client.mock)
        }
        jvmTest.dependencies {
            implementation(libs.slf4jSimple)
            implementation(libs.kotest.junit)
            implementation(libs.ktor.client.cio)
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
