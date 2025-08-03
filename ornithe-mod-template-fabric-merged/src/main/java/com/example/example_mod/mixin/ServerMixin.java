package com.example.example_mod.mixin;

import com.example.example_mod.ExampleMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.handler.CommandRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class ServerMixin {

    // Shadow the method to get the CommandRegistry.
    // This allows us to access the private method from the mixin.
    // The exact method name might vary, but it's likely 'getCommandHandler' or similar.
    @Shadow
    public abstract CommandRegistry getCommandHandler();

    @Inject(method = "startServer", at = @At("RETURN"))
    private void onServerStarted(CallbackInfo ci) {
        // This method is called after the server has successfully started.
        // The command handler is now available, and we can safely register our command.
        ExampleMod.registerCommands(this.getCommandHandler());
    }
}
