package me.virtualbyte.byteutils.storage.serializables;

import me.virtualbyte.byteutils.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class CompressedItemStack {

    private ItemStack itemStack;

    /*
     * Construct a compressed itemstack instance.
     *
     * @param compressedItem The compressed itemstack in string format.
     */
    public CompressedItemStack(String compressedItem) {
        String[] itemVariables = compressedItem.split(":");

        int item   = Integer.valueOf(itemVariables[0]);
        int data   = Integer.valueOf(itemVariables[1]);
        int amount = Integer.valueOf(itemVariables[2]);

        String name = itemVariables[3].replaceAll("^\"|\"$", "");

        List<String>                  lore         = new ArrayList<String>();
        HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();

        for (int x = 4; x < itemVariables.length; x++) {
            if (itemVariables[x].startsWith("lore-")) {
                String loreMessage = ChatColor.translateAlternateColorCodes('&', itemVariables[x].replaceFirst("lore-", "").replace("\"", "")
                        .replaceAll("^\"|\"$", ""));
            } else if (itemVariables[x].startsWith("ench-")) {
                String variable = itemVariables[x].replace("\"", "").replaceFirst("ench-", "");
                String[] enchantVariables = variable.split("=");

                Enchantment enchantment = Enchantment.getByName(enchantVariables[0].toUpperCase());
                int level = Integer.valueOf(enchantVariables[1]);

                enchantments.put(enchantment, level);
            }
        }

        init(item, data, amount, name, lore, enchantments);
    }

    /*
     * Construct a compressed itemstack instance.
     *
     * @param itemStack The item to be compressed.
     */
    public CompressedItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /*
     * Initialize the instance.
     *
     * @param id           ID of the itemstack.
     * @param data         Data of the itemstack.
     * @param name         Name of the ItemStack.
     * @param lore         List of the lores.
     * @param enchantments List of the enchantments and their levels.
     */
    private void init(int id, int data, int amount, String name, List<String> lore, HashMap<Enchantment, Integer> enchantments) {
        this.itemStack = new ItemStack(Material.getMaterial(id), amount, (byte) data);

        if (!(itemStack.getType() == Material.AIR)) {
            ItemMeta itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(Material.getMaterial(id));
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

            if (!lore.isEmpty()) {
                itemMeta.setLore(lore);
            }

            itemStack.setItemMeta(itemMeta);
            itemStack.addUnsafeEnchantments(enchantments);
        }
    }

    /*
     * Get itemstack.
     *
     * @return Item.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /*
     * Get compressed item.
     *
     * @return Compressed itemstack in string format.
     */
    public String compress() {
        String compressedItemStack = String.valueOf(this.itemStack.getTypeId()) + ":" + String.valueOf(this.itemStack.getData()) + ":"
                + String.valueOf(this.itemStack.getAmount()) + ":" + Messages.chatColourToString(this.itemStack.getItemMeta().getDisplayName())
                + ":";

        for (String loreMessage : this.itemStack.getItemMeta().getLore()) {
            compressedItemStack += "lore-" + Messages.chatColourToString(loreMessage) + ":";
        }

        for (Enchantment enchantment : this.itemStack.getEnchantments().keySet()) {
            compressedItemStack += "ench-" + enchantment.getName().toUpperCase() + "=" + this.itemStack.getEnchantmentLevel(enchantment) + ":";
        }

        return compressedItemStack;
    }

}
