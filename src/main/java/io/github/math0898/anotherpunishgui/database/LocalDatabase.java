package io.github.math0898.anotherpunishgui.database;

import io.github.math0898.anotherpunishgui.AnotherPunishGUI;
import io.github.math0898.anotherpunishgui.structures.Log;
import io.github.math0898.anotherpunishgui.structures.Note;
import io.github.math0898.anotherpunishgui.structures.Report;
import io.github.math0898.anotherpunishgui.structures.YamlSavable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The local database saves data locally in the plugin data folder.
 *
 * @author Sugaku
 */
public class LocalDatabase implements Database {

    /**
     * Creates a new LocalDatabase and sets up the file structure.
     */
    public LocalDatabase () {
        File file = new File("./plugins/AnotherPunishGUI/data/");
        if (!file.exists()) file.mkdir();
        try {
            for (DataTypes d : DataTypes.values())
                new File("./plugins/AnotherPunishGUI/data/" + d.getFileName()).createNewFile();
        } catch (IOException exception) {
            Plugin plugin = AnotherPunishGUI.getInstance();
            plugin.getLogger().log(Level.SEVERE, "Failed to initialize database: " + exception.getMessage());
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    /**
     * Saves an arbitrary YamlSavable object to the given database location.
     *
     * @param savable The item to save to the database.
     * @param type    The type of data the savable represents. Used to determine specific location.
     */
    @Override
    public void save (YamlSavable savable, DataTypes type) {
        File file = new File("./plugins/AnotherPunishGUI/data/" + type.getFileName());
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        Set<String> keys = yaml.getKeys(false);
        ConfigurationSection section = yaml.createSection(keys.size() + "");
        savable.save(section);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            Logger logger = AnotherPunishGUI.getInstance().getLogger();
            logger.log(Level.SEVERE, "Failed to save data: " + savable);
            logger.log(Level.SEVERE, "Reason: " + exception.getMessage());
        }
    }

    /**
     * Deletes any existing notes on the given player.
     *
     * @param player The player to clear of any notes.
     */
    @Override
    public void clearNotes (Player player) {
        File file = new File("./plugins/AnotherPunishGUI/data/" + DataTypes.NOTE.getFileName());
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        Set<String> keys = yaml.getKeys(false);
        List<String> toRemove = new ArrayList<>();
        for (String s : keys)
            if (yaml.getString(s + ".uuid", "").equalsIgnoreCase(player.getUniqueId().toString()))
                toRemove.add(s);
        toRemove.forEach((s) -> yaml.set(s, null));
        try {
            yaml.save(file);
        } catch (IOException exception) {
            Logger logger = AnotherPunishGUI.getInstance().getLogger();
            logger.log(Level.SEVERE, "Failed to clear player note data.");
            logger.log(Level.SEVERE, "Reason: " + exception.getMessage());
        }
    }
    /**
     * Removes a note from the given player that starts with the passed string.
     *
     * @param player The player to add a note for.
     * @param note   The note to remove.
     */
    @Override
    public void removeNote (Player player, String note) {
        File file = new File("./plugins/AnotherPunishGUI/data/" + DataTypes.NOTE.getFileName());
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        Set<String> keys = yaml.getKeys(false);
        List<String> toRemove = new ArrayList<>();
        for (String s : keys)
            if (yaml.getString(s + ".uuid", "").equalsIgnoreCase(player.getUniqueId().toString()))
                if (yaml.getString(s + ".data", "\n").startsWith(note)) // No data should start with \n. This only serves to more simply prevent NullPointerException.
                    toRemove.add(s);
        toRemove.forEach((s) -> yaml.set(s, null));
        try {
            yaml.save(file);
        } catch (IOException exception) {
            Logger logger = AnotherPunishGUI.getInstance().getLogger();
            logger.log(Level.SEVERE, "Failed to remove note data.");
            logger.log(Level.SEVERE, "Reason: " + exception.getMessage());
        }
    }

    /**
     * Returns the full list of reports.
     *
     * @return The full list of reports in the database.
     */
    @Override
    public List<Report> getReports () {
        List<Report> reports = new ArrayList<>();
        File file = new File("./plugins/AnotherPunishGUI/data/" + DataTypes.REPORT.getFileName());
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        Set<String> keys = yaml.getKeys(false);
        for (String s : keys) {
            ConfigurationSection section = yaml.getConfigurationSection(s);
            if (section == null) continue;
            reports.add(new Report(section));
        }
        return reports;
    }

    /**
     * Grabs all the logs on a specific player.
     *
     * @param player The player to grab the logs of.
     */
    @Override
    public List<Log> getLogs (Player player) {
        List<Log> logs = new ArrayList<>();
        File file = new File("./plugins/AnotherPunishGUI/data/" + DataTypes.LOG.getFileName());
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        Set<String> keys = yaml.getKeys(false);
        for (String s : keys) {
            ConfigurationSection section = yaml.getConfigurationSection(s);
            if (section == null) continue;
            Log log = new Log(section);
            if (log.user().equals(player.getUniqueId()))
                logs.add(log);
        }
        return logs;
    }

    /**
     * Grabs all the notes listed on a specific player.
     *
     * @param player The player to get the notes of.
     * @return The list of notes on this player.
     */
    @Override
    public List<Note> getNotes(Player player) {
        List<Note> notes = new ArrayList<>();
        File file = new File("./plugins/AnotherPunishGUI/data/" + DataTypes.NOTE.getFileName());
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        Set<String> keys = yaml.getKeys(false);
        for (String s : keys) {
            ConfigurationSection section = yaml.getConfigurationSection(s);
            if (section == null) continue;
            Note note = new Note(section);
            if (note.target().equals(player.getUniqueId()))
                notes.add(note);
        }
        return notes;
    }
}
