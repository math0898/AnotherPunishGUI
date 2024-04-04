package io.github.math0898.anotherpunishgui.commands;

import io.github.math0898.anotherpunishgui.gui.ReportsGUI;
import io.github.math0898.utils.commands.Subcommand;
import io.github.math0898.utils.gui.GUIManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The ReportsSubcommand is used to show a list of recent reports to staff.
 *
 * @author Sugaku
 */
public class ReportsSubcommand implements Subcommand { // todo: Implement

    /**
     * Creates a new ReportsSubcommand.
     */
    public ReportsSubcommand () {
        GUIManager.getInstance().addGUI("reports", new ReportsGUI());
    }

    /**
     * Called whenever specifically a player executes this command.
     *
     * @param player The player who ran this command.
     * @param args   The arguments they passed to the command.
     */
    @Override
    public boolean onPlayerCommand (Player player, String[] args) {
        GUIManager.getInstance().openGUI("reports", player);
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
        return new ArrayList<>();
    }
}
