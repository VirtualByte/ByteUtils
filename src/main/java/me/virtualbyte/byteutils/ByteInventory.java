package me.virtualbyte.byteutils;

import me.virtualbyte.byteutils.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class ByteInventory {

    private Inventory inventory;

    /*
     * Construct a ByteInventory instance.
     *
     * @param name Name of the inventory.
     * @param size Size of the inventory, must be multiple of 9 or it'll be rounded up.
     */
    public ByteInventory(String name, int size) {
        size = ((size + 8) / 9) * 9;

        this.inventory = Bukkit.createInventory(null, size, Messages.format(name));
    }

    /*
     * Get inventory.
     *
     * @return Inventory.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /*
     * Get name of inventory.
     *
     * @return Name of inventory.
     */
    public String getName() {
        return this.inventory.getName();
    }

    /*
     * Get size of inventory.
     *
     * @return Size of inventor, multiple of 9.
     */
    public int getSize() {
        return this.inventory.getSize();
    }

    /*
     * Set an item for a slot.
     *
     * @param slot Slot to be selected.
     * @param item Item to be inserted into selected slot.
     */
    public void setItem(int slot, ItemStack item) {
        this.inventory.setItem(slot, item);
    }

}