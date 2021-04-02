package me.jacobtread.tread.module.modules.render

import me.jacobtread.tread.event.EventTarget
import me.jacobtread.tread.event.OverlayRenderEvent
import me.jacobtread.tread.module.Category
import me.jacobtread.tread.module.Module
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Formatting

class OverlayModule : Module("Overlay", Category.RENDER) {

    init {
        isToggled = true
    }


    @EventTarget
    fun onOverlay(event: OverlayRenderEvent) {
        val stack: MatrixStack = event.matrixStack
        mc.textRenderer.drawWithShadow(stack,"Tread", 5F, 5F, -1)
        stack.scale(0.5f, 0.5f, 0.5f)
        mc.textRenderer.drawWithShadow(stack, Formatting.DARK_PURPLE.toString() + "v1.0", 10F, 18F + (mc.textRenderer.fontHeight * 2), -1)
        stack.scale(2f, 2f, 2f)
    }

}