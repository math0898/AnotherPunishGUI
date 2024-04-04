package io.github.math0898.anotherpunishgui.database;

import io.github.math0898.anotherpunishgui.structures.Report;
import org.bukkit.entity.Player;

/**
 * A database saves and loads file from a generic save location.
 *
 * @author Sugaku
 */
public interface Database {

    /**
     * Saves a given report to the database.
     *
     * @param report The report to save.
     */
    void saveReport (Report report);

    /**
     * Deletes any existing notes on the given player.
     *
     * @param player The player to clear of any notes.
     */
    void clearNotes (Player player);

    /**
     * Adds a note to the given player.
     *
     * @param player The player to add a note for.
     * @param note   The note to add.
     */
    void addNote (Player player, String note);

    /**
     * Removes a note from the given player that starts with the passed string.
     *
     * @param player The player to add a note for.
     * @param note   The note to remove.
     */
    void removeNote (Player player, String note);
}
