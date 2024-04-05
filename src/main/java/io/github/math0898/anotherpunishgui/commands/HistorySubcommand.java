package io.github.math0898.anotherpunishgui.commands;

import io.github.math0898.anotherpunishgui.gui.HistoryGUI;
import io.github.math0898.utils.commands.BetterCommand;
import io.github.math0898.utils.commands.Subcommand;
import io.github.math0898.utils.gui.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The HistorySubcommand is used to show a target's punishment history.
 *
 * @author Sugaku
 */
public class HistorySubcommand implements Subcommand {

    /**
     * Called whenever specifically a player executes this command.
     *
     * @param player The player who ran this command.
     * @param args   The arguments they passed to the command.
     */
    @Override
    public boolean onPlayerCommand (Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /s history");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "We could not find a player by that name.");
            return true;
        }
        GUI gui = new HistoryGUI(target);
        gui.openInventory(player);
        return true;
    }

    /**
     * Called whenever an unspecified sender executes this command. This could include console and command blocks.
     *
     * @param sender The sender who ran this command.
     * @param args   The arguments they passed to the command.
     */
    @Override
    public boolean onNonPlayerCommand (CommandSender sender, String[] args) {
        if (sender instanceof Player p) return onPlayerCommand(p, args); // This exists due to the way SRootCommand was implemented.
        sender.sendMessage(ChatColor.RED + "This command can only be ran as a player.");
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
        if (args.length == 1) Bukkit.getOnlinePlayers().forEach((p) -> toReturn.add(p.getName()));
        return BetterCommand.everythingStartsWith(toReturn, args[0], false);
    }
}
