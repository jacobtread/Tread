package me.jacobtread.tread.event

import me.jacobtread.tread.Tread
import me.jacobtread.tread.module.Module

open class Event {

    var isCancelled: Boolean = false

    fun trigger() {
        Tread.INSTANCE.eventManager.get(this.javaClass).forEach {
            try {
                it.method.invoke(it.owner, this)
            } catch (e: RuntimeException) {
            }
        }
    }

}