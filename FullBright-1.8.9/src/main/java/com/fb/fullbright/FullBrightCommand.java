package com.fb.fullbright;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;
import net.minecraft.client.Minecraft;

public class FullBrightCommand extends CommandBase {

    @Override
    public String getCommandName() { return "gamma"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "gamma <amount>"; }

    @Override
    public int getRequiredPermissionLevel() { return 0; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new WrongUsageException(getCommandUsage(sender));
        }

        final int amount = parseInt(args[0], 0, 100);
        Minecraft.getMinecraft().gameSettings.gammaSetting = amount;
        
        FullBrightConfig.setGamma(amount);
        sender.addChatMessage(new ChatComponentText("Gamma: " + amount));
    }
}
