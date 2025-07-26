package com.sevenday.commons.db

import kotlin.test.Test
import kotlin.test.assertTrue   // or JUnit, see below

class DBTest {

    @Test
    fun `can open and close pool`() {
        DB.connect(
            user = "sa",
            pass = "",
            jdbcUrlOverride = "jdbc:h2:mem:test;MODE=MySQL;DATABASE_TO_UPPER=false"
        )
        DB.hikari.close()
        assertTrue(DB.hikari.isClosed)
    }
}