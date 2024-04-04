package io.github.math0898.anotherpunishgui.database;

import lombok.Getter;

/**
 * An enum of datatypes. Each is stored in a slightly different location depending on the Database type.
 *
 * @author Sugaku
 */
@Getter
public enum DataTypes {

    /**
     * Notes are quick messages about a player.
     */
    NOTE("notes.yml"),

    /**
     * Reports are unofficial player accounts.
     */
    REPORT("reports.yml"),

    /**
     * Punishments are enacted by staff.
     */
    PUNISHMENT("history.yml");

    /**
     * The yml file to save this DataType to when saving locally.
     * -- GETTER --
     * Accessor method for the file this DataType should be saved at.
     */
    private final String fileName;

    /**
     * Creates a new DataType enum value.
     *
     * @param fileName The file name of the file to save it.
     */
    DataTypes (String fileName) {
        this.fileName = fileName;
    }
}
