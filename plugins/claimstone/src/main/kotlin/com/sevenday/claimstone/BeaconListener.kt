package com.sevenday.claimstone

import com.sevenday.commons.db.CountryRepo
import com.sevenday.commons.mm
import org.bukkit.Material
import org.bukkit.block.Biome
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class BeaconListener(private val plugin: ClaimstonePlugin) : Listener {

    @EventHandler
    fun onBeaconClick(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        val block = e.clickedBlock ?: return
        if (block.type != Material.BEACON) return

        val biome = block.biome.toKey().asString()         // e.g. minecraft:savanna
        val existing = CountryRepo.findByBiome(biome)
        if (existing != null) {
            e.player.sendMessage("<red>Biome already owned!".mm())
            return
        }

        val country = CountryRepo.create(biome, e.player.uniqueId, block.location)
        e.player.sendMessage("<green>You claimed $biome!".mm())
        plugin.logger.info("${e.player.name} claimed $biome at ${block.location}")
    }

    /** paper-api 1.21 helper */
    private fun Biome.toKey() = org.bukkit.Registry.BIOME.getKey(this)!!
}