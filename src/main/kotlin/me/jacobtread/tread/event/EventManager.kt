package me.jacobtread.tread.event

import me.jacobtread.tread.Tread
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import java.lang.reflect.Method
import java.util.concurrent.CopyOnWriteArrayList

class EventManager {

    private val registry: HashMap<Class<Event>, CopyOnWriteArrayList<EventData>> = HashMap()


    init {
        val mc: MinecraftClient = MinecraftClient.getInstance()
        // Hooking some fabric events
        ClientTickEvents.START_CLIENT_TICK.register {
            it?.player ?: return@register
            it.world ?: return@register
            TickEvent().trigger()
        }
        HudRenderCallback.EVENT.register { matrixStack: MatrixStack, _ ->
            mc.window.run {
                OverlayRenderEvent(matrixStack, width, height).trigger()
            }
        }
    }

    fun register(owner: Any) {
        owner.javaClass.declaredMethods.forEach {
            if (it.isAnnotationPresent(EventTarget::class.java)) {
                if (it.parameterTypes.isNotEmpty()) {
                    @Suppress("UNCHECKED_CAST") val event: Class<Event> =
                        (it.parameterTypes[0] ?: return@forEach) as Class<Event>
                    val eventTarget: EventTarget = it.getAnnotation(EventTarget::class.java)
                    val eventData: CopyOnWriteArrayList<EventData> = registry.getOrDefault(event, CopyOnWriteArrayList())
                    eventData.add(EventData(owner, it, eventTarget.priority.level))
                    registry[event] = eventData
                } else {
                    Tread.LOGGER.warn("Invalid event target \"${it.name}\" in ${owner.javaClass.name}")
                }
            }
        }
    }

    fun ignore(owner: Any) {
        registry.values.forEach { it.removeIf { data -> data.owner == owner } }
    }

    fun get(event: Class<Event>): List<EventData> {
        return if (event in registry) {
            val list: List<EventData> = registry[event] ?: emptyList()
            list.sortedBy { it.priority }
            list
        } else {
            emptyList()
        }
    }

}

data class EventData(val owner: Any, val method: Method, val priority: Int)

enum class Priority(val level: Int) {
    LOWEST(-2),
    LOW(-1),
    NORMAL(0),
    HIGH(1),
    HIGHEST(2)
}

annotation class EventTarget(val priority: Priority = Priority.NORMAL)
