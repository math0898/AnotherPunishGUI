package io.github.math0898.utils.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * A Subcommand sits underneath another command and runs only if the first argument is for this command.
 *
 * @author Sugaku
 */
public interface Subcommand {

    /**
     * Called whenever specifically a player executes this command.
     *
     * @param player The player who ran this command.
     * @param args   The arguments they passed to the command.
     */
    boolean onPlayerCommand (Player player, String[] args);

    /**
     * Called whenever an unspecified sender executes this command. This could include console and command blocks.
     *
     * @param sender The sender who ran this command.
     * @param args   The arguments they passed to the command.
     */
    boolean onNonPlayerCommand (CommandSender sender, String[] args);

    /**
     * Called whenever a command sender is trying to tab complete a command.
     *
     * @param sender The sender who is tab completing this command.
     * @param args   The current arguments they have typed.
     */
    List<String> simplifiedTab (CommandSender sender, String[] args);
}
