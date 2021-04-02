package me.jacobtread.tread

import net.fabricmc.api.ClientModInitializer

class Initializer : ClientModInitializer {
    override fun onInitializeClient() {
        Tread.INSTANCE.start()
    }
}
