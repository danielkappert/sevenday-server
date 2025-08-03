import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

val junitVersion = "5.10.2"
val mockBukkitVersion = "3.133.2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    implementation(project(":plugins:commons"))
    implementation("org.mariadb.jdbc:mariadb-java-client:3.4.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.21:$mockBukkitVersion")
    testRuntimeOnly("com.h2database:h2:2.2.224")
}

tasks.test {
    useJUnitPlatform()
}

tasks.processResources {
    inputs.property("projectVersion", project.version)
    filesMatching("plugin.yml") {
        expand("projectVersion" to project.version)
    }
}

// -- pack the plugin (and only its runtime deps) into claimstone-*-all.jar
tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName.set("claimstone")
}