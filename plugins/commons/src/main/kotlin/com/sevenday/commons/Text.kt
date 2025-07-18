package com.sevenday.commons

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

private val mm: MiniMessage = MiniMessage.miniMessage()

fun String.mm(vararg resolvers: TagResolver): Component =
    mm.deserialize(this, *resolvers)