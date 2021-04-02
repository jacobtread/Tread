package me.jacobtread.tread.module

import me.jacobtread.tread.Tread
import net.minecraft.client.MinecraftClient

open class Module(val name: String, var category: Category, var bind: Int = 0) {

    var isToggled: Boolean = false
        set(value) {
            if (value) onEnable()
            else onDisable()
            field = value
        }

    val mc: MinecraftClient = MinecraftClient.getInstance()

    open fun onEnable() {
        Tread.INSTANCE.eventManager.register(this)
    }

    open fun onDisable() {
        Tread.INSTANCE.eventManager.ignore(this)
    }

}