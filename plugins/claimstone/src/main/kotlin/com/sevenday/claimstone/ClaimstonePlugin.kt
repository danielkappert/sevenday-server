package com.sevenday.claimstone

import com.sevenday.commons.db.Countries
import com.sevenday.commons.db.DB
import com.sevenday.commons.launch
import com.sevenday.commons.mm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greater
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.deleteWhere

open class ClaimstonePlugin : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()

        if (!DB.isConnected()) {
            val cfg = config.getConfigurationSection("database")!!
            DB.connect(
                host = cfg.getString("host")!!,
                port = cfg.getInt("port"),
                db   = cfg.getString("name")!!,
                user = cfg.getString("user")!!,
                pass = cfg.getString("pass")!!
            )
        }

        // ---------- listeners & commands ----------
        server.pluginManager.registerEvents(BeaconListener(this), this)
        getCommand("country")!!.setExecutor(CountryCommand())

        // ---------- scheduled health-decay ----------
        launch {
            while (true) {
                delay(10 * 60 * 1000L)      // 10 min
                decayHealth()
            }
        }

        logger.info("<gold>Claimstone enabled & DB connected!".mm().toString())
    }

    override fun onDisable() {
        DB.close()
    }

    /** Drops every country's health by 1; deletes rows at zero */
    private suspend fun decayHealth() = withContext(Dispatchers.IO) {
        transaction {
            Countries.update({ Countries.health greater 0 }) {
                with(SqlExpressionBuilder) { it[Countries.health] = Countries.health - 1 }
            }
            Countries.deleteWhere { Countries.health lessEq 0 }
        }
    }
}
