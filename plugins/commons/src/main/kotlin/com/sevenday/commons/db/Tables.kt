package com.sevenday.commons.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.dao.id.LongIdTable

object Players : LongIdTable("players") {
    val uuid     = char("uuid", 36).uniqueIndex()
    val name     = varchar("name", 16)
    val coins    = long("coins")
    val joinedAt = datetime("joined_at")
    val lastSeen = datetime("last_seen")
}

object Countries : LongIdTable("countries") {
    val biomeKey    = varchar("biome_key", 64).uniqueIndex()
    val ownerUuid   = char("owner_uuid", 36)
    val beaconColor = varchar("beacon_color", 16)
    val radius      = integer("radius")
    val health      = integer("health")
    val createdAt   = datetime("created_at")
    val claimX      = integer("claim_x")
    val claimY      = integer("claim_y")
    val claimZ      = integer("claim_z")
}