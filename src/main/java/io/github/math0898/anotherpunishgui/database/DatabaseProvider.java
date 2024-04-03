package io.github.math0898.anotherpunishgui.database;

/**
 * The DatabaseProvider is a singleton that determines which database to utilize at runtime.
 *
 * @author Sugaku
 */
public class DatabaseProvider {

    /**
     * The active DatabaseProvider instance.
     */
    private static DatabaseProvider instance = null;

    /**
     * The database to use during runtime.
     */
    private final Database database;

    /**
     * Creates the DatabaseProvider and determines which database to utilize.
     */
    private DatabaseProvider () {
        database = new LocalDatabase(); // todo: Implement.
    }

    /**
     * Static accessor to the DatabaseProvider.
     *
     * @return The active DatabaseProvider.
     */
    public static DatabaseProvider getInstance () {
        if (instance == null) instance = new DatabaseProvider();
        return instance;
    }

    /**
     * An accessor to the active Database.
     *
     * @return The active Database.
     */
    public Database getDatabase () {
        return database;
    }
}
