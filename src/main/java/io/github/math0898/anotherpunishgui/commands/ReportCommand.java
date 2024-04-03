package io.github.math0898.anotherpunishgui.commands;

import io.github.math0898.anotherpunishgui.ConfigManager;
import io.github.math0898.anotherpunishgui.database.Database;
import io.github.math0898.anotherpunishgui.database.DatabaseProvider;
import io.github.math0898.anotherpunishgui.structures.Report;
import io.github.math0898.utils.commands.BetterCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The report command allows players to report other players for any reason but recommends a list of potential reasons.
 *
 * @author Sugaku
 */
public class ReportCommand extends BetterCommand {

    /**
     * Creates a new BetterCommand with the given name.
     */
    public ReportCommand () {
        super("report");
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
            send(sender, ChatColor.RED + "Usage: /report <player> <reason>");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            send(sender, ChatColor.RED + "We could not find a player by name: " + args[0]);
            return true;
        }
        Report report = new Report(sender.getName(), player.getUniqueId(), args[1], System.currentTimeMillis(), false);
        Database database = DatabaseProvider.getInstance().getDatabase();
        database.saveReport(report);
        send(sender, ChatColor.GREEN + "You have reported " + player.getName() + " for " + args[1] + ". Staff will investigate.");
        return true;
    }

    /**
     * Called whenever a command sender is trying to tab complete a command.
     *
     * @param sender The sender who is tab completing this command.
     * @param args   The current arguments they have typed.
     */
    @Override
    public List<String> simplifiedTab(CommandSender sender, String[] args) {
        List<String> toReturn = new ArrayList<>();
        if (args.length == 0) Bukkit.getOnlinePlayers().forEach((p) -> toReturn.add(p.getName()));
        else if (args.length == 1) toReturn.addAll(ConfigManager.getInstance().getReportReasons());
        return everythingStartsWith(toReturn, args[args.length - 1], false);
    }
}
