package io.github.math0898.anotherpunishgui.commands;

import io.github.math0898.anotherpunishgui.ConfigManager;
import io.github.math0898.anotherpunishgui.gui.PunishGUI;
import io.github.math0898.anotherpunishgui.punisher.Punisher;
import io.github.math0898.anotherpunishgui.punisher.PunisherProvider;
import io.github.math0898.anotherpunishgui.structures.Punishment;
import io.github.math0898.utils.commands.BetterCommand;
import io.github.math0898.utils.gui.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The Punish subcommand punishes the given player. Will open an inventory if a punishment is not configured.
 *
 * @author Suagku
 */
public class PunishSubcommand extends BetterCommand {

    /**
     * Creates a new BetterCommand with the given name.
     */
    public PunishSubcommand () {
        super("punish-subcommand", ChatColor.DARK_GRAY + "[" + ChatColor.RED + "APGUI" + ChatColor.DARK_GRAY + "] ", false);
    }

    /**
     * Called whenever specifically a player executes this command.
     *
     * @param player The player who ran this command.
     * @param args   The arguments they passed to the command.
     */
    @Override
    public boolean onPlayerCommand (Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /s punish <player> (punishment)");
            return true;
        }
        if (args.length == 2) return onPlayerCommand(player, args);
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "We could not find player: " + args[0]);
            return true;
        }
        GUI gui = new PunishGUI(target);
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
        if (sender instanceof Player p && args.length < 2) return onPlayerCommand(p, args); // This exists due to the way SRootCommand was implemented.
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /s punish <player> <punishment>");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "We could not find player: " + args[0]);
            return true;
        }
        Punishment punishment = null;
        List<Punishment> punishments = ConfigManager.getInstance().getPunishments();
        for (Punishment p : punishments)
            if (args[1].equalsIgnoreCase(p.internalName()))
                punishment = p;
        if (punishment == null) {
            sender.sendMessage(ChatColor.RED + "We could not find a punishment by: " + args[1]);
            return true;
        }
        Punisher punisher = PunisherProvider.getInstance().getPunisher();
        punisher.punish(punishment, sender, player);
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
        if (args.length <= 1) Bukkit.getOnlinePlayers().forEach((p) -> toReturn.add(p.getName()));
        else if (args.length == 2) {
            List<Punishment> punishments = ConfigManager.getInstance().getPunishments();
            punishments.forEach((p) -> toReturn.add(p.internalName()));
        }
        return everythingStartsWith(toReturn, args[args.length - 1], false);
    }
}
