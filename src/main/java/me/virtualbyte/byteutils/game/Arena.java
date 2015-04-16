package me.virtualbyte.byteutils.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class Arena {

    private String         name;
    private boolean        canBuild;
    private List<Material> canBeBuilt;

    private Location boundary1;
    private Location boundary2;

    public Arena(String name) {
        this.name = name;
        this.canBuild = false;
        this.canBeBuilt = new ArrayList<Material>();
    }

    /*
     * Return name of the arena.
     *
     * @return name of arena.
     */
    public String getName() {
        return this.name;
    }

    /*
     * Return whether the arena can be built on or not.
     *
     * @return whether building is allowed within arena.
     */
    public boolean canBuild() {
        return this.canBuild;
    }

    /*
     * Return the list of materials that can be destroyed/built.
     *
     * @return list of materials that is allowed to be destroyed or built.
     */
    public List<Material> getCanBeBuilt() {
        return this.canBeBuilt;
    }

    /*
     * Set what you can build on the map, canBuild must be true for this to have an effect.
     *
     * @param canBeBuilt What can be build/destroyed on the map/arena.
     */
    public void setCanBeBuilt(List<Material> canBeBuilt) {
        this.canBeBuilt = canBeBuilt;
    }

    /*
     * Set whether you can build on the arena.
     *
     * @param canBuild Can build on the map or not.
     */
    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    /*
     * Return boundary 1.
     *
     * @return location of boundary 1.
     */
    public Location getBoundary1() {
        return this.boundary1;
    }

    /*
     * Return boundary 2.
     *
     * @return location of boundary 2.
     */
    public Location getBoundary2() {
        return this.boundary2;
    }

    /*
     * Returns whether player is inside arena or not.
     *
     * @return whether the player is inside the arena.
     */
    public boolean isPlayerInside(Player player) {
        return player.getLocation().getX() >= Math.min(this.boundary1.getX(), this.boundary2.getX()) && player.getLocation().getX() <= Math.max(this.boundary1.getX(), this.boundary2.getX())
                && player.getLocation().getY() >= Math.min(this.boundary1.getY(), this.boundary2.getY() + 1) && player.getLocation().getY() <= Math.max(this.boundary1.getY(), this.boundary2.getY() + 1)
                && player.getLocation().getZ() >= Math.min(this.boundary1.getZ(), this.boundary2.getZ()) && player.getLocation().getZ() <= Math.max(this.boundary1.getZ(), this.boundary2.getZ());
    }

}