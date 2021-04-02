package me.jacobtread.tread

import me.jacobtread.tread.event.EventManager
import me.jacobtread.tread.module.ModuleManager
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class Tread {

    lateinit var eventManager: EventManager
    lateinit var moduleManager: ModuleManager

    fun start() {
        eventManager = EventManager()
        moduleManager = ModuleManager()
    }

    fun stop() {

    }

    companion object {

        val LOGGER: Logger = LogManager.getLogger("Tread")
        val INSTANCE: Tread = Tread()

    }

}