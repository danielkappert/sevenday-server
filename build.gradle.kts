plugins {
    // These are declared globally, applied selectively in sub-projects
    kotlin("jvm") version "2.0.0" apply false      // Kotlin compiler
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

allprojects {
    group = "com.sevenday"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

subprojects {
    // Ensure Java 21 byte-code across the board
    tasks.withType<JavaCompile>().configureEach {
        options.release.set(21)
    }
}