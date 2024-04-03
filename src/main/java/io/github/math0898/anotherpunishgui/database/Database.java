package io.github.math0898.anotherpunishgui.database;

import io.github.math0898.anotherpunishgui.structures.Report;

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
}
