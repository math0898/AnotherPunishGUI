package io.github.math0898.anotherpunishgui.database;

import io.github.math0898.anotherpunishgui.structures.Report;
import org.bukkit.entity.Player;

/**
 * The local database saves data locally in the plugin data folder.
 *
 * @author Sugaku
 */
public class LocalDatabase implements Database { // todo: Consider caching changes between reads/writes.

    /**
     * Saves a given report to the database.
     *
     * @param report The report to save.
     */
    @Override
    public void saveReport (Report report) {
        // todo: Implement!
        System.out.println("Saved report: " + report.toString());
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
     * Adds a note to the given player.
     *
     * @param player The player to add a note for.
     * @param note   The note to add.
     */
    @Override
    public void addNote (Player player, String note) {
        // todo: Implement.
        System.out.println("Saved note: " + note);
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
