package dev.solcraft.command.mixin;

import dev.solcraft.command.CommandMod;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.noise.ImprovedNoise;
import net.minecraft.world.gen.noise.PerlinNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PerlinNoise.class)
public class PerlinNoiseMixinNew {

	@Shadow
	private int levels;

	@Shadow
	private ImprovedNoise[] noiseLevels;

	/**
	 * @author SolDev
	 * @reason idk how else I'd do this
	 */
	@Overwrite(remap = false)
	public double[] getRegion(double[] values, int x, int y, int z, int sizeX, int sizeY, int sizeZ, double scaleX, double scaleY, double scaleZ) {
		if (values == null) {
			values = new double[sizeX * sizeY * sizeZ];
		} else {
			for(int i = 0; i < values.length; ++i) {
				values[i] = (double)0.0F;
			}
		}

		double d = (double)1.0F;

		for(int j = 0; j < this.levels; ++j) {
			double e = (double)x * d * scaleX;
			double f = (double)y * d * scaleY;
			double g = (double)z * d * scaleZ;
			if (CommandMod.genFarlands) {
				long l = MathHelper.lfloor(e);
				long m = MathHelper.lfloor(g);
				e -= (double) l;
				g -= (double) m;
				l %= 16777216L;
				m %= 16777216L;
				e += (double) l;
				g += (double) m;
			}
			this.noiseLevels[j].add(values, e, f, g, sizeX, sizeY, sizeZ, scaleX * d, scaleY * d, scaleZ * d, d);
			d /= (double)2.0F;
		}

		return values;
	}
}
