package com.sevenday.claimstone

import org.bukkit.plugin.java.JavaPlugin
import com.sevenday.commons.mm

open class ClaimstonePlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("<gold>Claimstone enabled!".mm().toString())
    }
}