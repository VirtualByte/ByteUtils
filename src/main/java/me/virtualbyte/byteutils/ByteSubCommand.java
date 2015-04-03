package me.virtualbyte.byteutils;

import me.virtualbyte.byteutils.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public abstract class ByteSubCommand {

    public abstract String getName();

    public abstract String getPermission();

    public abstract String getPermissionMessage();

    public abstract int getMinArgs();

    public abstract String getNotEnoughArgsMessage();

    public abstract boolean isPlayerOnly();

    public abstract void run(CommandSender sender, String label, String[] args);

    public void onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (isPlayerOnly() && !(commandSender instanceof Player)) {
            Messages.sendMessage(commandSender, getName() + " is a player-only command.");
            return;
        }

        if (!commandSender.hasPermission(getPermission())) {
            Messages.sendMessage(commandSender, getPermissionMessage());
            return;
        }

        if (args.length - 1 < getMinArgs()) {
            Messages.sendMessage(commandSender, getNotEnoughArgsMessage());
            return;
        }

        run(commandSender, label, args);
    }

}
