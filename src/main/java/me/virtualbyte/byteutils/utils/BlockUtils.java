package me.virtualbyte.byteutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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

    /*
     * Create an ItemStack.
     *
     * @param material Material of the itemstack.
     * @param amount   Amount of items in the itemstack.
     * @param data     Data (ex. wool colour) of the itemstack.
     * @param name     Name of the itemstack, can be coloured.
     * @param lore     List of lore for the itemstack.
     */
    public static ItemStack createItemStack(Material material, int amount, int data, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount, (short) data);

        ItemMeta itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(material);
        itemMeta.setDisplayName(Messages.format(name));

        List<String> colouredLore = new ArrayList<String>();

        for (String aLore : lore) {
            colouredLore.add(Messages.format(aLore));
        }

        itemMeta.setLore(colouredLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    /*
     * Convert a ChatColor to wool data.
     *
     * @param colour ChatColor to convert.
     */
    public static int chatColourToWool(ChatColor colour) {
        switch(colour) {
            case BLACK:
                return 15;
            case DARK_BLUE:
                return 11;
            case DARK_GREEN:
                return 13;
            case DARK_AQUA:
                return 9;
            case DARK_RED:
                return 13;
            case DARK_PURPLE:
                return 10;
            case GOLD:
                return 1;
            case GRAY:
                return 7;
            case DARK_GRAY:
                return 8;
            case BLUE:
                return 3;
            case GREEN:
                return 5;
            case AQUA:
                return 3;
            case RED:
                return 6;
            case LIGHT_PURPLE:
                return 2;
            case YELLOW:
                return 4;
            case WHITE:
                return 0;
            default:
                return 0;
        }
    }

}