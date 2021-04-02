package me.jacobtread.tread.event

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack

class TickEvent : Event()

class OverlayRenderEvent(val matrixStack: MatrixStack, val width: Int, val height: Int) : Event()

class KeyboardEvent(val keyCode: Int, val scanCode: Int) : Event()