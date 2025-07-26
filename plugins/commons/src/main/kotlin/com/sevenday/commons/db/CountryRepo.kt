package com.sevenday.commons.db

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

data class Country(
    val id: Long,
    val biomeKey: String,
    val owner: UUID,
    val radius: Int,
    val health: Int,
    val x: Int,
    val y: Int,
    val z: Int
)

object CountryRepo {
    fun findByBiome(biome: String): Country? = transaction {
        Countries.select { Countries.biomeKey eq biome }
            .singleOrNull()
            ?.toCountry()
    }

    fun create(biome: String, owner: UUID, loc: org.bukkit.Location): Country = transaction {
        val id = Countries.insertAndGetId {
            it[biomeKey]  = biome
            it[ownerUuid] = owner.toString()
            it[radius]    = 64
            it[health]    = 100
            it[claimX]    = loc.blockX
            it[claimY]    = loc.blockY
            it[claimZ]    = loc.blockZ
            it[beaconColor] = "WHITE"
        }.value
        Countries.select { Countries.id eq id }.single().toCountry()
    }

    private fun ResultRow.toCountry() = Country(
        id         = this[Countries.id].value,
        biomeKey   = this[Countries.biomeKey],
        owner      = UUID.fromString(this[Countries.ownerUuid]),
        radius     = this[Countries.radius],
        health     = this[Countries.health],
        x          = this[Countries.claimX],
        y          = this[Countries.claimY],
        z          = this[Countries.claimZ]
    )
}