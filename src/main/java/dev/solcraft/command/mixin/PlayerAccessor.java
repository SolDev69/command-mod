package dev.solcraft.command.mixin;

import net.minecraft.entity.mob.player.PlayerAbilities;
import net.minecraft.entity.mob.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerEntity.class)
public interface PlayerAccessor {
	@Accessor("abilities")
	PlayerAbilities getAbilities();

	@Accessor("pressedJumpTwiceTimer")
	int getPressedJumpTwiceTimer();

	@Accessor("pressedJumpTwiceTimer")
	void setPressedJumpTwiceTimer(int i);
}
