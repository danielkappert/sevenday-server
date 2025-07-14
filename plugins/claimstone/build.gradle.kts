import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    implementation(project(":plugins:commons"))
}

// -- pack the plugin (and only its runtime deps) into claimstone-*-all.jar
tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName.set("claimstone")
    // relocate internal libs if you add any later, e.g. Gson
}