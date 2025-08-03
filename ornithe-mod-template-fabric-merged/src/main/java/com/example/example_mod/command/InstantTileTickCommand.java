package com.example.example_mod.command;

import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.source.CommandSource;
import net.minecraft.server.command.exception.IncorrectUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.server.command.Command; // Added for compareTo method

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A command to toggle the `doTicksImmediately` property on the world.
 * This is useful for debugging and can speed up block ticks significantly.
 */
public class InstantTileTickCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "instanttiletick";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(CommandSource source) {
        return "commands.instanttiletick.usage";
    }

    @Override
    public void run(CommandSource source, String[] args) {
        if (args.length != 1) {
            throw new IncorrectUsageException("commands.instanttiletick.usage");
        }

        boolean enableTicks;
        String arg = args[0].toLowerCase();

        if ("true".equals(arg)) {
            enableTicks = true;
        } else if ("false".equals(arg)) {
            enableTicks = false;
        } else {
            throw new IncorrectUsageException("commands.instanttiletick.invalid_argument", new Object[]{args[0]});
        }

        try {
            World world = MinecraftServer.getInstance().worlds[0];
            world.doTicksImmediately = enableTicks;

            String status = enableTicks ? "commands.instanttiletick.status.enabled" : "commands.instanttiletick.status.disabled";
            
            sendSuccess(source, "commands.instanttiletick.success", new Object[]{status});

        } catch (Exception e) {
            e.printStackTrace();
            throw new IncorrectUsageException("commands.instanttiletick.error", new Object[]{e.getMessage()});
        }
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String[] args) {
        if (args.length == 1) {
            return suggestMatching(args, "true", "false");
        }
        return null;
    }

    /**
     * Required to override the compareTo method from the Comparable interface.
     * This method safely casts the object and uses the superclass's compareTo.
     */
    @Override
    public int compareTo(Object o) {
        return super.compareTo((Command)o);
    }
}
