package me.virtualbyte.byteutils.game;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * ByteUtils - Developed by VirtualByte (Lewes D. B.)
 */
public abstract class Game {

    private JavaPlugin plugin;
    private String     name;
    private List<Team> teams;
    private Arena      arena;
    private int        maxPlayers;
    private int        minPlayers;
    private int        countdownTime;
    private int        gameTime;
    private GameStage  stage;

    /*
     * Construct a game instance.
     *
     * @param name  Name of the game.
     * @param teams Teams that will be part of the game.
     */
    public Game(JavaPlugin plugin, String name, Team... teams) {
        this.plugin = plugin;
        this.name = name;
        this.teams = new ArrayList<Team>();
        this.maxPlayers = -1;
        this.minPlayers = -1;
        this.countdownTime = -1;
        this.gameTime = -1;
        this.stage = GameStage.COUNTDOWN;

        plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

            @Override
            public void run() {
                task();
            }

        }, 20L, 20L);
    }

    /*
     * Get name of the game.
     *
     * @return Name of the game.
     */
    public String getName() {
        return this.name;
    }

    /*
     * Get teams.
     *
     * @return Teams in the game.
     */
    public List<Team> getTeams() {
        return this.teams;
    }

    /*
     * Get arena.
     *
     * @return The arena that is being played.
     */
    public Arena getArena() {
        return this.arena;
    }

    /*
     * Get maximum amount of players.
     *
     * @return Maximum amount of players allowed.
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /*
     * Get minimum amount of players.
     *
     * @return Minimum amount of players allowed.
     */
    public int getMinPlayers() {
        return this.minPlayers;
    }

    /*
     * Get countdown time.
     *
     * @return Countdown time.
     */
    public int getCountdownTime() {
        return this.countdownTime;
    }

    /*
     * Get game time.
     *
     * @return Countdown time.
     */
    public int getGameTime() {
        return this.gameTime;
    }

    /*
     * Get stage
     *
     * @return Stage the game is in.
     */
    public GameStage getStage() {
        return this.stage;
    }

    /*
     * Set arena
     *
     * @param arena Arena to be set.
     */
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    /*
     * Set the maximum amount of players.
     *
     * @param maxPlayers Max players.
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /*
     * Set the minimum amount of players.
     *
     * @param minPlayers Min players.
     */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /*
     * Set the countdown.
     *
     * @param countdown Countdown time.
     */
    public void setCountdown(int countdown) {
        this.countdownTime = countdown;
    }

    /*
     * Set the game time.
     *
     * @param game Game time.
     */
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    /*
     * Set the stage.
     *
     * @param stage Stage to be set to.
     */
    public void setStage(GameStage stage) {
        this.stage = stage;
    }

    /*
     * Begin the countdown.
     */
    public void begin() {
        this.stage = GameStage.COUNTDOWN;
    }

    /*
     * Whether the game can end or not.
     *
     * @return If game can end or not.
     */
    public abstract boolean canGameEnd();

    public void onGameStart() {

    }

    public void onGameEnd() {

    }

    public void task() {

    }

}