package io.github.math0898.anotherpunishgui.commands;

import io.github.math0898.utils.commands.BetterCommand;
import io.github.math0898.utils.commands.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * The SRootCommand handles the root part of any given commands and passes them onto other command handlers.
 *
 * @author Sugaku
 */
public class SRootCommand extends BetterCommand {

    /**
     * A map of Subcommands. The key value is the first word to activate the subcommand.
     */
    private final Map<String, Subcommand> subcommands = new HashMap<>();

    /**
     * Creates a new BetterCommand with the given name.
     */
    public SRootCommand () {
        super("s");
        subcommands.put("notes", new NoteSubcommand());
        subcommands.put("punish", new PunishSubcommand());
        subcommands.put("history", new HistorySubcommand());
        subcommands.put("reports", new ReportsSubcommand());
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
        if (args.length == 0) {
            send(sender, ChatColor.RED + "Usage: /s <punish/history/reports/notes>...");
            return true;
        }
        Subcommand sub = subcommands.get(args[0]);
        if (sub == null) {
            send(sender, ChatColor.RED + "Usage: /s <punish/history/reports/notes>...");
            return true;
        }
        sub.onNonPlayerCommand(sender, Arrays.copyOfRange(args, 1, args.length));
        return true;
    }

    /**
     * Called whenever a command sender is trying to tab complete a command.
     *
     * @param sender The sender who is tab completing this command.
     * @param args   The current arguments they have typed.
     */
    @Override
    public List<String> simplifiedTab (CommandSender sender, String[] args) {
        List<String> toReturn = new ArrayList<>();
        if (args.length == 0) toReturn.addAll(subcommands.keySet());
        else if (args.length > 1) {
            Subcommand sub = subcommands.get(args[0]);
            if (sub == null) return toReturn;
            else return sub.simplifiedTab(sender, Arrays.copyOfRange(args, 1, args.length));
        }
        return everythingStartsWith(toReturn, args[0], false);
    }
}
