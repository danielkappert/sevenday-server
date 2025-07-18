plugins {
    kotlin("jvm")
}

dependencies {
    api(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    api("net.kyori:adventure-text-minimessage:4.23.0")
}