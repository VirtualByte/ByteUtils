package me.virtualbyte.byteutils.storage.serializables;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class CompressedLocation {

    private Location location;

    /*
     * Construct a compressed location instance.
     *
     * @param location Location to compress.
     */
    public CompressedLocation(Location location) {
        this.location = location;
    }

    /*
     * Construct a compressed location instance.
     *
     * @param compresedLocation Location that has been compressed.
     */
    public CompressedLocation(String compressedLocation) {
        if (!compressedLocation.contains(":") ||
                compressedLocation.split(":").length != 6) {
            return;
        }

        String[] args = compressedLocation.split(":");

        String world = args[0];
        int    x     = Integer.parseInt(args[1]);
        int    y     = Integer.parseInt(args[2]);
        int    z     = Integer.parseInt(args[3]);
        float  yaw   = Float.parseFloat(args[4]);
        float  pitch = Float.parseFloat(args[5]);

        if (Bukkit.getWorld(world) == null) {
            return;
        }

        this.location = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    /*
     * Compress the location into a serializable string.
     *
     * @return A compressed location in a string.
     */
    public String compress() {
        String compressedLocation = "";

        compressedLocation += this.location.getWorld().getName() + ":";
        compressedLocation += Integer.toString(this.location.getBlockX()) + ":";
        compressedLocation += Integer.toString(this.location.getBlockY()) + ":";
        compressedLocation += Integer.toString(this.location.getBlockZ()) + ":";
        compressedLocation += Float.toString(this.location.getYaw()) + ":";
        compressedLocation += Float.toString(this.location.getPitch());

        return compressedLocation;
    }

    /*
     * Return location of compressed location.
     *
     * @return The location.
     */
    public Location getLocation() {
        return this.location;
    }

}