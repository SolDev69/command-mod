package dev.solcraft.command.mixin.flight;


import dev.solcraft.command.CommandMod;
import dev.solcraft.command.lib.Command;
import net.minecraft.entity.mob.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class FlightPlayerEntityMixin {
	@Inject(method = "moveRelative", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/player/PlayerEntity;tickNonRidingMovementRelatedStats(DDD)V"))
	public void moveRelativeInject(float sideways, float forwards, CallbackInfo ci) {
		IFlightEntity flightEntity = (IFlightEntity) this;
		IFlightMobEntity fmb = (IFlightMobEntity) this;
		if (CommandMod.isFlying) {
			double g = flightEntity.getVelocityY();
//			float f7 = this.flyingSpeed;
//			this.flyingSpeed = 0.05F;
			fmb.invokeMoveRelative(sideways, forwards);
//			this.velocityY = g * 0.6;
//			flightEntity.setVelocityY(f7);
		} else {
//			super.moveRelative(sideways, forwards);
		}
	}
}
