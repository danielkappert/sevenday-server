package com.sevenday.claimstone

import com.sevenday.commons.db.CountryRepo
import com.sevenday.commons.mm
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CountryCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        cmd: Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        if (sender !is Player) {
            sender.sendMessage("<red>Players only!".mm())
            return true
        }

        if (args.isEmpty()) {
            sender.sendMessage("<yellow>/country <info|invite|members>".mm())
            return true
        }

        when (args[0].lowercase()) {
            "info" -> {
                val biomeKey = sender.location.block.biome
                    .let { org.bukkit.Registry.BIOME.getKey(it)!!.asString() }

                val country = CountryRepo.findByBiome(biomeKey)
                if (country == null) {
                    sender.sendMessage("<yellow>This biome is unclaimed".mm())
                } else {
                    sender.sendMessage(
                        "<aqua>Owner:</aqua> <white>${country.owner}".mm()
                    )
                    sender.sendMessage(
                        "<aqua>Health:</aqua> <white>${country.health}".mm()
                    )
                }
            }

            // “invite” and “members” will be fleshed out later
            else -> sender.sendMessage("<red>Unknown sub-command".mm())
        }
        return true
    }
}
