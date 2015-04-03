package me.virtualbyte.byteutils;

import me.virtualbyte.byteutils.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public abstract class ByteCommand extends Command implements CommandExecutor {

    protected ByteCommand(String name) {
        super(name);
    }

    public abstract String getName();

    public abstract String getPermission();

    public abstract String getPermissionMessage();

    public abstract int getMinArgs();

    public abstract String getNotEnoughArgsMessage();

    public abstract boolean isPlayerOnly();

    public abstract ByteSubCommand[] getSubCommands();

    public abstract void run(CommandSender sender, String label, String[] args);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (isPlayerOnly() && !(commandSender instanceof Player)) {
            Messages.sendMessage(commandSender, getName() + " is a player-only command.");
            return true;
        }

        if (!commandSender.hasPermission(getPermission())) {
            Messages.sendMessage(commandSender, getPermissionMessage());
            return true;
        }

        if (args.length < getMinArgs()) {
            Messages.sendMessage(commandSender, getNotEnoughArgsMessage());
            return true;
        }

        if (args.length != 0 && getSubCommands().length != 0) {
            for (ByteSubCommand subCommand : getSubCommands()) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.onCommand(commandSender, command, label, args);
                    return true;
                }
            }
        }

        run(commandSender, label, args);

        return true;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        onCommand(sender, this, commandLabel, args);

        return true;
    }

}