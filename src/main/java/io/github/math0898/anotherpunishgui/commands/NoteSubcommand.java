package io.github.math0898.anotherpunishgui.commands;

import io.github.math0898.anotherpunishgui.database.DataTypes;
import io.github.math0898.anotherpunishgui.database.Database;
import io.github.math0898.anotherpunishgui.database.DatabaseProvider;
import io.github.math0898.anotherpunishgui.structures.Note;
import io.github.math0898.utils.commands.BetterCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The NoteSubcommand is used by staff to write notes about specific problem players.
 *
 * @author Sugaku
 */
public class NoteSubcommand extends BetterCommand {

    /**
     * Creates a new BetterCommand with the given name.
     */
    public NoteSubcommand () {
        super("note-subcommand", ChatColor.DARK_GRAY + "[" + ChatColor.RED + "APGUI" + ChatColor.DARK_GRAY + "] ", false);
    }

    /**
     * Called whenever specifically a player executes this command.
     *
     * @param player The player who ran this command.
     * @param args   The arguments they passed to the command.
     */
    @Override
    public boolean onPlayerCommand (Player player, String[] args) {
        return onNonPlayerCommand(player, args);
    }

    /**
     * Called whenever an unspecified sender executes this command. This could include console and command blocks.
     *
     * @param sender The sender who ran this command.
     * @param args   The arguments they passed to the command.
     */
    @Override
    public boolean onNonPlayerCommand (CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /s notes <player> <add/remove/clear>");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "We could not find a player by that name.");
            return true;
        }
        Database database = DatabaseProvider.getInstance().getDatabase();
        if (args[1].equalsIgnoreCase("clear")) {
            database.clearNotes(player);
            sender.sendMessage(ChatColor.GREEN + "Cleared note.");
            return true;
        }
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Usage: /s notes <player> <add/remove/clear>");
            return true;
        }
        StringBuilder note = new StringBuilder(args[2]);
        for (int i = 3; i < args.length; i++)
            note.append(" ").append(args[i]);
        if (args[1].equalsIgnoreCase("add")) {
            sender.sendMessage(ChatColor.GREEN + "Note added!");
            Note obj = new Note(sender.getName(), player.getUniqueId(), note.toString());
            database.save(obj, DataTypes.NOTE);
        }
        else if (args[1].equalsIgnoreCase("remove")) {
            sender.sendMessage(ChatColor.GREEN + "Note removed!");
            database.removeNote(player, note.toString());
        }
        else sender.sendMessage(ChatColor.RED + "Usage: /s notes <player> <add/remove/clear>");
        return true;
    }

    /**
     * Called whenever a command sender is trying to tab complete a command.
     *
     * @param sender The sender who is tab completing this command.
     * @param args   The current arguments they have typed.
     */
    @Override
    public List<String> simplifiedTab (CommandSender sender, String[] args) { // todo: Implement remove.
        List<String> toReturn = new ArrayList<>();
        if (args.length <= 1) Bukkit.getOnlinePlayers().forEach((p) -> toReturn.add(p.getName()));
        else if (args.length == 2) toReturn.addAll(Arrays.asList("add", "remove", "clear"));
        else if (args.length == 3 && args[1].equalsIgnoreCase("add")) return List.of("<txt>");
        else if (args[1].equalsIgnoreCase("remove")) { // implied args length of >= 3
            // todo: Query a list of notes on the player provided in arg 1, then provide tab completion. Make sure to cache.
        }
        return everythingStartsWith(toReturn, args[args.length - 1], false);
    }
}
