package io.github.math0898.anotherpunishgui.database;

import io.github.math0898.anotherpunishgui.structures.Report;

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
}
