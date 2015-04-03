package me.virtualbyte.byteutils.game;

import me.virtualbyte.byteutils.game.events.PlayerJoinTeamEvent;
import me.virtualbyte.byteutils.game.events.PlayerLeaveTeamEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class Team {

    private String       name;
    private List<String> players;

    /*
     * Construct a team instance.
     *
     * @param name Name of the team.
     */
    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<String>();
    }

    /*
     * Returns name of the team.
     *
     * @return Name of the team.
     */
    public String getName() {
        return this.name;
    }

    /*
     * Returns list of players in the team.
     *
     * @return players in the team.
     */
    public List<String> getPlayers() {
        return this.players;
    }

    /*
     * Add a player to the team.
     *
     * @param player Player to be added.
     */
    public void addPlayer(Player player) {
        this.players.add(player.getName());

        Bukkit.getPluginManager().callEvent(new PlayerJoinTeamEvent(player, this));
    }

    /*
     * Remove a player from the team.
     *
     * @param player Player to be removed.
     */
    public void removePlayer(Player player) {
        if (this.players.contains(player.getName())) {
            this.players.remove(player.getName());
        }

        Bukkit.getPluginManager().callEvent(new PlayerLeaveTeamEvent(player, this));
    }

    /*
     * Return whether the player is in the team.
     *
     * @param player Player to be searched for.
     * @return whether the player is in the team or not.
     */
    public boolean isPlayerInTeam(Player player) {
        return this.players.contains(player.getName());
    }

}