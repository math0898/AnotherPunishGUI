package io.github.math0898.anotherpunishgui.database;

import io.github.math0898.anotherpunishgui.structures.YamlSavable;
import org.bukkit.entity.Player;

/**
 * The local database saves data locally in the plugin data folder.
 *
 * @author Sugaku
 */
public class LocalDatabase implements Database { // todo: Consider caching changes between reads/writes.

    /**
     * Saves an arbitrary YamlSavable object to the given database location.
     *
     * @param savable The item to save to the database.
     * @param type    The type of data the savable represents. Used to determine specific location.
     */
    @Override
    public void save (YamlSavable savable, DataTypes type) {
        // todo: Implement.
        System.out.println("Saved: " + savable.toString() + " of type: " + type.toString());
    }

    /**
     * Deletes any existing notes on the given player.
     *
     * @param player The player to clear of any notes.
     */
    @Override
    public void clearNotes (Player player) {
        // todo: Implement!
        System.out.println("Cleared notes: " + player.toString());
    }
    /**
     * Removes a note from the given player that starts with the passed string.
     *
     * @param player The player to add a note for.
     * @param note   The note to remove.
     */
    @Override
    public void removeNote (Player player, String note) {
        // todo: Implement.
        System.out.println("Removed note: " + note);
    }
}
