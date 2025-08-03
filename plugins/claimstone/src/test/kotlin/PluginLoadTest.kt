package com.sevenday.claimstone

import be.seeseemelk.mockbukkit.MockBukkit
import com.sevenday.commons.db.DB
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class PluginLoadTest {

    @Test
    fun `plugin enables without errors`() {

        DB.connect(
            jdbcUrlOverride = "jdbc:h2:mem:test;MODE=MySQL",
            user = "sa",
            pass = ""
        )

        val server = MockBukkit.mock()
        try {
            val plugin = MockBukkit.load(ClaimstonePlugin::class.java)
            assertNotNull(plugin, "Plugin failed to load")
        } finally {
            MockBukkit.unmock()
        }
    }
}