plugins {
    kotlin("jvm")
}

val exposed = "0.50.1"

dependencies {
    api(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    api("net.kyori:adventure-text-minimessage:4.23.0")
    api("com.zaxxer:HikariCP:5.1.0")
    api("org.jetbrains.exposed:exposed-core:$exposed")
    api("org.jetbrains.exposed:exposed-dao:$exposed")
    api("org.jetbrains.exposed:exposed-jdbc:$exposed")
    api("org.jetbrains.exposed:exposed-java-time:$exposed")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.4.0")

    // --- testing ---
    testImplementation(kotlin("test"))
    testImplementation("com.h2database:h2:2.2.224")
}

tasks.test {
    useJUnitPlatform()
}