package io.github.math0898.anotherpunishgui.database;

import io.github.math0898.anotherpunishgui.structures.Log;
import io.github.math0898.anotherpunishgui.structures.Report;
import io.github.math0898.anotherpunishgui.structures.YamlSavable;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * A database saves and loads file from a generic save location.
 *
 * @author Sugaku
 */
public interface Database {

    /**
     * Saves an arbitrary YamlSavable object to the given database location.
     *
     * @param savable The item to save to the database.
     * @param type    The type of data the savable represents. Used to determine specific location.
     */
    void save (YamlSavable savable, DataTypes type);

    /**
     * Deletes any existing notes on the given player.
     *
     * @param player The player to clear of any notes.
     */
    void clearNotes (Player player);

    /**
     * Removes a note from the given player that starts with the passed string.
     *
     * @param player The player to add a note for.
     * @param note   The note to remove.
     */
    void removeNote (Player player, String note);

    /**
     * Returns the full list of reports.
     *
     * @return The full list of reports in the database.
     */
    List<Report> getReports ();

    /**
     * Grabs all the logs on a specific player.
     *
     * @param player The player to grab the logs of.
     */
    List<Log> getLogs (Player player);
}
