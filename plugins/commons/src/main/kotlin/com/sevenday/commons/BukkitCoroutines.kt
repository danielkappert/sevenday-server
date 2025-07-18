package com.sevenday.commons

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.bukkit.plugin.Plugin

fun Plugin.launch(block: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(SupervisorJob() + Dispatchers.Default).launch(block = block)