package com.ha.hidearmor;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;
import net.minecraft.client.Minecraft;

public class HideArmorCommand extends CommandBase {

    @Override
    public String getCommandName() { return "hidearmor"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "hidearmor <true/false>"; }

    @Override
    public int getRequiredPermissionLevel() { return 0; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new WrongUsageException(getCommandUsage(sender));
        }

        final boolean toggle = Boolean.valueOf(args[0]);
        HideArmorConfig.setToggle(toggle);

        sender.addChatMessage(new ChatComponentText("HideArmor : " + toggle));
    }
}
