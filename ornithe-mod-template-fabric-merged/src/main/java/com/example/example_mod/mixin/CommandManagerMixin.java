package com.example.example_mod.mixin;

import com.example.example_mod.ExampleMod;
import net.minecraft.server.command.handler.CommandManager;
import net.minecraft.server.command.handler.CommandRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onCommandManagerInit(CallbackInfo ci) {
        // This debug message will appear in your logs if the mixin is applied correctly.
        System.out.println("DEBUG: CommandManagerMixin is running and injecting into the constructor!");
        
        // Cast the 'this' instance to CommandRegistry and register the command.
        // Injecting at the end of the constructor is a reliable way to ensure the
        // CommandManager is fully initialized before we register our command.
        ExampleMod.registerCommands((CommandRegistry) (Object) this);
    }
}