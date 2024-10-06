import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotest.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
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
            implementation("io.ktor:ktor-client-cio:2.3.12")
        }
    }
}
val detekt by configurations.creating

dependencies {
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.23.3")
    detekt("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
}

tasks.register<JavaExec>("detekt") {
    mainClass = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = detekt

    val input = projectDir
    val config = "$projectDir/detekt.yml"
    val exclude = ".*/build/.*,.*/resources/.*"
    val report = "sarif:${layout.buildDirectory.file("reports/detekt/detekt.sarif").get()}"
    val params = listOf("-i", input, "-c", config, "-ex", exclude, "-r", report, "--build-upon-default-config")

    args(params)
}

tasks.register<JavaExec>("detektFix") {
    mainClass = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = detekt

    val input = projectDir
    val config = "$projectDir/detekt.yml"
    val exclude = ".*/build/.*,.*/resources/.*"
    val params = listOf("-i", input, "-c", config, "-ex", exclude, "--auto-correct", "--build-upon-default-config")
    setIgnoreExitValue(true)

    args(params)
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
