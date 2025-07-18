package com.sevenday.claimstone

import be.seeseemelk.mockbukkit.MockBukkit
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class PluginLoadTest {

    @Test
    fun `plugin enables without errors`() {
        val server = MockBukkit.mock()     // returns ServerMock
        try {
            val plugin = MockBukkit.load(ClaimstonePlugin::class.java)
            assertNotNull(plugin, "Plugin failed to load")
        } finally {
            MockBukkit.unmock()           // ensure cleanup
        }
    }
}