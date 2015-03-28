package me.virtualbyte.byteutils.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class PlayerUtils {

    /*
     * Heal a player.
     *
     * @param player Player to be healed.
     */
    public static void heal(Player player) {
        player.setFireTicks(0);
        player.setFallDistance(0F);
        player.setHealth(player.getMaxHealth());

        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }

    /*
     * Teleport a player to a location.
     *
     * @param player   Player to be teleported.
     * @param location Location to be teleported to.
     */
    public static void teleport(Player player, Location location) {
        player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    /*
     * Teleport a player to a player.
     *
     * @param player   Player to be teleported.
     * @param toPlayer Player to be teleported to.
     */
    public static void teleport(Player player, Player toPlayer) {
        teleport(player, toPlayer.getLocation());
    }

}
