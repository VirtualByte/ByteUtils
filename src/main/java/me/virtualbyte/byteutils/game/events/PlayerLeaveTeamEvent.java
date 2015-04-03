package me.virtualbyte.byteutils.game.events;

import me.virtualbyte.byteutils.game.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public class PlayerLeaveTeamEvent extends PlayerEvent {

    private Team team;

    public PlayerLeaveTeamEvent(Player who, Team team) {
        super(who);

        this.team = team;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    /*
     * Get team that player left.
     *
     * @return team player left.
     */
    public Team getTeam() {
        return this.team;
    }

}
