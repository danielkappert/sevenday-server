package com.sevenday.commons.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Players : Table("players") {
    val id       = long("id").autoIncrement()
    val uuid     = char("uuid", 36).uniqueIndex()
    val name     = varchar("name", 16)
    val coins    = long("coins")
    val joinedAt = datetime("joined_at")
    val lastSeen = datetime("last_seen")

    override val primaryKey = PrimaryKey(id)
}

object Countries : Table("countries") {
    val id          = long("id").autoIncrement()
    val biomeKey    = varchar("biome_key", 64).uniqueIndex()
    val ownerUuid   = char("owner_uuid", 36)
    val beaconColor = varchar("beacon_color", 16)
    val radius      = integer("radius")
    val health      = integer("health")
    val createdAt   = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}