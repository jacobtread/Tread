package me.jacobtread.tread.module

import me.jacobtread.tread.module.modules.render.OverlayModule
import java.util.concurrent.CopyOnWriteArrayList

class ModuleManager {

    private val modules: CopyOnWriteArrayList<Module> = CopyOnWriteArrayList()

    init {
        modules.apply {
            add(OverlayModule())
        }
    }


}