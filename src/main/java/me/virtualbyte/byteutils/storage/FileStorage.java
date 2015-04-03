package me.virtualbyte.byteutils.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class FileStorage {

    private File file;
    private FileConfiguration config;

    /*
     * Construct a FileStorage instance.
     *
     * @param name   Name of the file.
     * @param plugin Instance of the plugin.
     */
    public FileStorage(String name, JavaPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        this.file.getParentFile().mkdirs();

        try {
            this.file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    /*
     * Get the file.
     *
     * @return configuration's file.
     */
    public File getFile() {
        return this.file;
    }

    /*
     * Get the configuration.
     *
     * @return the YAML configuration.
     */
    public FileConfiguration getConfig() {
        return this.config;
    }

    /*
     * Save the configuration to the file.
     */
    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}