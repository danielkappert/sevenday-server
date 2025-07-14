package com.sevenday.claimstone

import org.bukkit.plugin.java.JavaPlugin

class ClaimstonePlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("ClaimstonePlugin loaded – hello Sevenday Server!")
    }
}