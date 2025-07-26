package com.sevenday.commons.db


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DB {

    lateinit var hikari: HikariDataSource
        private set

    fun connect(
        host: String = "localhost",
        port: Int    = 3306,
        db: String   = "",
        user: String,
        pass: String,
        poolSize: Int = 10,
        jdbcUrlOverride: String? = null
    ) {
        val cfg = HikariConfig().apply {
            jdbcUrl = jdbcUrlOverride
                ?: "jdbc:mariadb://$host:$port/$db"
            username = user
            password = pass
            maximumPoolSize = poolSize
            driverClassName = if (jdbcUrlOverride?.startsWith("jdbc:h2") == true)
                "org.h2.Driver" else "org.mariadb.jdbc.Driver"
            validate()
        }
        hikari = HikariDataSource(cfg)
        Database.connect(hikari)
    }

    fun isConnected(): Boolean = this::hikari.isInitialized && !hikari.isClosed
    fun close() { if (isConnected()) hikari.close() }
}