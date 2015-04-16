package me.virtualbyte.byteutils.utils;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldedit.util.io.file.FilenameException;
import me.virtualbyte.byteutils.exceptions.UnsupportedPluginException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @authors desht, VirtualByte
 * <p/>
 * A wrapper class for the WorldEdit terrain loading & saving API to make things a little
 * simple for other plugins to use.
 */
public class ByteSchematic {

    private static final String EXTENSION = "schematic";

    private final WorldEdit we;
    private final LocalSession localSession;
    private final EditSession editSession;
    private final LocalPlayer localPlayer;

    /**
     * Constructor
     *
     * @param wep    the WorldEdit plugin instance
     * @param player the player to work with
     */
    public ByteSchematic(JavaPlugin plugin, Player player) throws UnsupportedPluginException {
        if(plugin instanceof WorldEditPlugin) {
            throw new UnsupportedPluginException("JavaPlugin parameter is an instance of WorldEditPlugin");
        }

        WorldEditPlugin wep = (WorldEditPlugin) plugin;
        we = wep.getWorldEdit();
        localPlayer = wep.wrapPlayer(player);
        localSession = we.getSession(localPlayer);
        editSession = localSession.createEditSession(localPlayer);
    }

    /**
     * Constructor
     *
     * @param wep   the WorldEdit plugin instance
     * @param world the world to work in
     */
    public ByteSchematic(WorldEditPlugin wep, World world) {
        we = wep.getWorldEdit();
        localPlayer = null;
        localSession = new LocalSession(we.getConfiguration());
        editSession = new EditSession(new BukkitWorld(world), we.getConfiguration().maxChangeLimit);
    }

    /**
     * Write the terrain bounded by the given locations to the given file as a MCedit format
     * schematic.
     *
     * @param saveFile a File representing the schematic file to create
     * @param l1       one corner of the region to save
     * @param l2       the corner of the region to save, opposite to l1
     * @throws DataException
     * @throws IOException
     */
    public void saveTerrain(File saveFile, Location l1, Location l2) throws FilenameException, DataException, IOException {
        Vector min = getMin(l1, l2);
        Vector max = getMax(l1, l2);

        try {
            saveFile = we.getSafeSaveFile(localPlayer,
                    saveFile.getParentFile(), saveFile.getName(),
                    EXTENSION, new String[]{EXTENSION});
        } catch (FilenameException e) {
            e.printStackTrace();
        }

        editSession.enableQueue();
        CuboidClipboard clipboard = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
        clipboard.copy(editSession);
        SchematicFormat.MCEDIT.save(clipboard, saveFile);
        editSession.flushQueue();
    }

    /**
     * Load the data from the given schematic file and paste it at the given location.  If the location is null, then
     * paste it at the saved data's origin.
     *
     * @param saveFile a File representing the schematic file to load
     * @param loc      the location to paste the clipboard at (may be null)
     */
    public void loadSchematic(File saveFile, Location loc) {
        try {
            saveFile = we.getSafeSaveFile(localPlayer,
                    saveFile.getParentFile(), saveFile.getName(),
                    EXTENSION, new String[]{EXTENSION});

            editSession.enableQueue();
            SchematicFormat.MCEDIT.load(saveFile).paste(editSession, getPastePosition(loc), false);
            editSession.flushQueue();

            if (localPlayer == null) {
                we.flushBlockBag(null, editSession);
            } else {
                we.flushBlockBag(localPlayer, editSession);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyClipboardException e) {
            e.printStackTrace();
        } catch (DataException e) {
            e.printStackTrace();
        } catch (MaxChangedBlocksException e) {
            e.printStackTrace();
        } catch (FilenameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the data from the given schematic file and paste it at the saved clipboard's origin.
     *
     * @param saveFile
     * @throws FilenameException
     * @throws DataException
     * @throws IOException
     * @throws MaxChangedBlocksException
     * @throws EmptyClipboardException
     */
    public void loadSchematic(File saveFile) {
        loadSchematic(saveFile, null);
    }

    private Vector getPastePosition(Location loc) throws EmptyClipboardException {
        if (loc == null)
            return localSession.getClipboard().getClipboard().getOrigin();
        else
            return new Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    private Vector getMin(Location l1, Location l2) {
        return new Vector(
                Math.min(l1.getBlockX(), l2.getBlockX()),
                Math.min(l1.getBlockY(), l2.getBlockY()),
                Math.min(l1.getBlockZ(), l2.getBlockZ())
        );
    }

    private Vector getMax(Location l1, Location l2) {
        return new Vector(
                Math.max(l1.getBlockX(), l2.getBlockX()),
                Math.max(l1.getBlockY(), l2.getBlockY()),
                Math.max(l1.getBlockZ(), l2.getBlockZ())
        );
    }
}