package com.sevenday.claimstone

import org.bukkit.plugin.java.JavaPlugin
import com.sevenday.commons.mm
import com.sevenday.commons.db.DB

open class ClaimstonePlugin : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()           // copies config.yml on first run
        val cfg = config.getConfigurationSection("database")!!
        DB.connect(
            host = cfg.getString("host")!!,
            port = cfg.getInt("port"),
            db   = cfg.getString("name")!!,
            user = cfg.getString("user")!!,
            pass = cfg.getString("pass")!!
        )

        logger.info("<gold>Claimstone enabled & DB connected!".mm().toString())
    }

    override fun onDisable() {
        DB.close()
    }
}