package me.jacobtread.tread.mixin

import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import me.jacobtread.tread.event.KeyboardEvent
import net.minecraft.client.Keyboard

@Mixin(Keyboard::class)
class KeyboardMixin {
    @Inject(method = ["onKey"],
        at = [At(value = "INVOKE",
            target = "Lnet/minecraft/client/util/InputUtil;fromKeyCode(II)Lnet/minecraft/client/util/InputUtil\$Key;")])
    private fun onKey(l: Long, i: Int, j: Int, k: Int, m: Int, ci: CallbackInfo) {
        KeyboardEvent(i, j).trigger()
    }
}