package me.virtualbyte.byteutils.storage;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class MySQLDatabase extends Database {

    /*
     * Construct a MySQL database instance.
     *
     * @param host     Host/IP of the MySQL database.
     * @param port     Port of the MySQL database.
     * @param database Database wanted to be access.
     * @param username Username to be authenticated with.
     * @param password Password for the user authentication.
     * @param plugin   Plugin for the schedulers to be assigned to.
     */
    public MySQLDatabase(String host, int port, String database, String username, String password, JavaPlugin plugin) {
        super("com.mysql.jdbc.Driver", "jdbc:mysql://" + host + ": " + port + "/" + database, username, password, plugin);
    }

}
