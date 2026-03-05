package dev.solcraft.command.mixin.flight;

import net.minecraft.client.entity.mob.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class FlightMixins {
	@Inject(method = "mobTick", at=@At("TAIL"))
	public void fly(CallbackInfo callbackInfo) {}
}
