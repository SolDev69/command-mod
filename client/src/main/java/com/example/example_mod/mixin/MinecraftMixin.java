package com.example.example_mod.mixin;

import com.example.example_mod.dev.solcraft.command.CommandRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.example_mod.dev.solcraft.command.CommandMod;

import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class MinecraftMixin {


	@Inject(method = "main", remap = false, at = @At("HEAD"))
	private static void exampleMod$onInit(CallbackInfo ci) {
		CommandMod.LOGGER.info("This line is printed by an example mod mixin!");
	}

	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isMultiplayer()Z"))
	private boolean allowChatInSP(Minecraft instance) {
		return true;
	}
}
