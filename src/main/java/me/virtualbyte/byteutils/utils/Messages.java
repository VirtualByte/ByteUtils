package me.virtualbyte.byteutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class Messages {

    private File              file;
    private FileConfiguration config;

    /*
     * Construct a messages instance.
     *
     * @param file            File containing the messages.
     * @param defaultMessages The messages that are referred to when it is missing in messages file.
     */
    public Messages(File file, DefaultMessages defaultMessages) {
        this.file = file;

        this.file.getParentFile().mkdirs();

        try {
            this.file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);

        for (String key : this.config.getKeys(false)) {
            if (!(this.config.get(key) instanceof String)) {
                set(key, null);
            }
        }

        if (defaultMessages != null) {
            for (String key : defaultMessages.getConfig().getKeys(true)) {
                if (!this.config.contains(key)) {
                    set(key, defaultMessages.getConfig().getString(key));
                }
            }
        }

        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Construct a messages instance.
     *
     * @param file File containing the messages.
     */
    public Messages(File file) {
        this(file, null);
    }

    /*
     * Construct a messages instance.
     *
     * @param plugin Plugin containing messages.yml in its data folder.
     */
    public Messages(JavaPlugin plugin) {
        this(new File(plugin.getDataFolder(), "messages.yml"), null);
    }

    /*
     * Construct a messages instance.
     *
     * @param plugin Plugin containing messages.yml in its data folder.
     * @param defaultMessages Messages to be referred to when it is missing in messages.yml
     */
    public Messages(JavaPlugin plugin, DefaultMessages defaultMessages) {
        this(new File(plugin.getDataFolder(), "messages.yml"), defaultMessages);
    }

    /*
     * Send a message which is formatted with colour to a sender.
     *
     * @param sendTo  Who the message will be sent to.
     * @param message Message to be sent.
     */
    public static void sendMessage(CommandSender sendTo, String message) {
        String[] messages;

        if (message.contains("\n")) {
            messages = message.split("\n");
        } else {
            List<String> messagesList = new ArrayList<String>();
            messagesList.add(message);

            messages = messagesList.toArray(new String[messagesList.size()]);
        }

        if (sendTo instanceof Player) {
            for (String toSend : messages) {
                sendTo.sendMessage(ChatColor.translateAlternateColorCodes('&', toSend));
            }
            return;
        }

        for (String toSend : messages) {
            sendTo.sendMessage(stripColors(toSend));
        }
    }

    /*
     * Broadcast a message to all players and console.
     *
     * @param message Message to be broadcast.
     */
    public static void broadcastMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendMessage(player, message);
        }

        sendMessage(Bukkit.getConsoleSender(), message);
    }

    /*
     * Broadcast a message to all players who have a certain permission.
     *
     * @param message    Message to be broadcast.
     * @param permission Permission required.
     */
    public static void broadcastMessage(String message, Permission permission) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                sendMessage(player, message);
            }
        }

        sendMessage(Bukkit.getConsoleSender(), message);
    }

    /*
     * Broadcast a message to all players who have a certain permission.
     *
     * @param message    Message to be broadcast.
     * @param permission Permission required in string format.
     */
    public static void broadcastMessage(String message, String permission) {
        broadcastMessage(message, new Permission(permission));
    }

    /*
     * Strip a message of colours including non-formatted colours.
     *
     * @param message Message to be stripped of colours.
     * @return        Message with colours stripped away.
     */
    public static String stripColors(String message) {
        return ChatColor.stripColor(Messages.format(message));
    }

    /*
     * Format a message containing colours.
     *
     * @param message Message to be formatted to include colours.
     * @return        Formatted message.
     */
    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /*
     * Convert a message containing strings into a serializable message with strings.
     *
     * @param message Message with colours to be converted.
     * @return        Message with converted colours.
     */
    public static String chatColourToString(String message) {
        return message.replace(ChatColor.COLOR_CHAR, '&');
    }

    /*
     * Set a message.
     *
     * @param key     Message key is defined to.
     * @param message Message to be set.
     */
    public void set(String key, String message) {
        this.config.set(key, message);
    }

    /*
     * Save the configuration containing the messages.
     */
    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Return the file.
     *
     * @return File containing messages.
     */
    public File getFile() {
        return this.file;
    }

    /*
     * Return the file's configuration.
     *
     * @return Configuration containing the messages.
     */
    public FileConfiguration getConfig() {
        return this.config;
    }

    /*
     * Get a formatted message from the given key.
     *
     * @param key Message that the key is defined to.
     * @turn      Formatted message found from the given key.
     */
    public String getMessage(String key) {
        return Messages.format(this.config.getString(key, ""));
    }

    /*
     * Get an unformatted message from the given key.
     *
     * @param key Message that the key is defined to.
     * @turn      Unformatted message found from the given key.
     */
    public String getFlatMessage(String key) {
        return stripColors(getMessage(key));
    }

}