package me.virtualbyte.byteutils.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class BlockUtils {

    /*
     * Set given block as a spawner.
     *
     * @param block      Block to be set.
     * @param entityType Entity for the spawner to be set to spawn.
     */
    public static void setSpawner(Block block, EntityType entityType) {
        block.setType(Material.MOB_SPAWNER);
        ((CreatureSpawner) block.getState()).setSpawnedType(entityType);
        ((CreatureSpawner) block.getState()).update(true, true);
    }

    /*
     * Get whether a string is a material.
     *
     * @param materialName Name of material to be checked.
     * @return             True if the material is a name of a material.
     */
    public static boolean isMaterial(String materialName) {
        try {
            Material.valueOf(materialName);
        } catch (IllegalArgumentException exception) {
            return false;
        } catch (NullPointerException exception) {
            return false;
        }

        return true;
    }

}