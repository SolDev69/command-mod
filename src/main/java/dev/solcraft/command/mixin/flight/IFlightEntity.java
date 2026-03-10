package dev.solcraft.command.mixin.flight;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface IFlightEntity {
	@Accessor("velocityX")
	double getVelocityX();

	@Accessor("velocityY")
	double getVelocityY();

	@Accessor("velocityY")
	void setVelocityY(double v);

	@Accessor("velocityZ")
	double getVelocityZ();
}
