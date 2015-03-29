package me.virtualbyte.byteutils.utils.aliases;

import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public enum EnchantmentAlias {

    ARROW_DAMAGE("power"),
    ARROW_FIRE("flame", "meleeflame"),
    ARROW_INFINITE("infinity", "unlimitedarrows", "arrowinfinite", "infinitearrows"),
    ARROW_KNOCKBACK("punch"),
    DAMAGE_ALL("sharpness", "sharp", "alldamage", "damage", "dmg"),
    DAMAGE_ATHROPODS("baneofarthropods", "athdmg", "athropodsdamage", "athropoddamage", "athropod", "athropods"),
    DAMAGE_UNDEAD("undead", "undeaddamage", "smite", "unddeaddmg"),
    DIG_SPEED("digspeed", "efficiency", "minespeed", "cutspeed", "ds", "eff"),
    DURABILITY("unbreaking", "dura"),
    THORNS("thorns", "highcrit", "highercrit", "thorn"),
    FIRE_ASPECT("fireaspect", "fire", "meleefire"),
    KNOCKBACK("kb", "kback", "knockb"),
    LOOT_BONUS_BLOCKS("fortune", "blockslootbonus", "fort", "lbb"),
    LOOT_BONUS_MOBS("looting", "loots", "loot", "moblootbonus", "lbm", "mobloot"),
    OXYGEN("respiration", "breathing", "gills", "breathe", "breath"),
    PROTECTION_ENVIRONMENTAL("protection", "p", "protect", "prot"),
    PROTECTION_EXPLOSIONS("explosionsprotection", "expprotect", "expprot", "expdamage", "expdmg", "blastprotection", "blastprotect", "bprotect", "bprotection"),
    PROTECTION_FALL("fallprotection", "featherfall", "featherfalling", "falldamage", "fallprot", "fallprotect"),
    PROTECTION_FIRE("fireprotection", "flameprotection", "fireprot", "flameprot", "firep", "flamep"),
    PROTECTHION_PROJECTILE("projectileprotection", "pprotect", "pprot", "projprot"),
    SILK_TOUCH("softtouch", "st"),
    WATER_WORKER("aquaaffinity", "waterminer", "watermining", "watermine"),
    LUCK("luckofsea", "luckofseas", "rodluck"),
    LURE("rodlure");

    private List<String> aliases = new ArrayList<String>();

    EnchantmentAlias(String... aliases) {
        for (String alias : aliases) {
            this.aliases.add(alias);
        }
    }

    /*
     * Get aliases for the enchantment, excluding Bukkit enchantment name.
     *
     * @return list of aliases for enchantment.
     */
    public List<String> getAliases() {
        return this.aliases;
    }

    /*
     * Return an enchantment by given alias.
     *
     * @param enchantmentName name/alias of enchantment.
     * @return Enchantment found.
     * @return Null if enchantment could not be found.
     */
    public Enchantment getByName(String enchantmentName) {
        for(EnchantmentAlias enchantmentAlias : EnchantmentAlias.values()) {
            if(enchantmentAlias.name().equalsIgnoreCase("enchantmentName")) {
                return Enchantment.getByName(enchantmentName.toUpperCase());
            }

            if(enchantmentAlias.name().replace("_", "").equalsIgnoreCase(enchantmentName)) {
                return Enchantment.getByName(enchantmentAlias.name().toUpperCase());
            }

            for(String alias : enchantmentAlias.getAliases()) {
                if(alias.equalsIgnoreCase(enchantmentName)) {
                    return Enchantment.getByName(enchantmentAlias.name().toUpperCase());
                }
            }
        }

        return null;
    }

}