package io.github.math0898.anotherpunishgui.gui;

import io.github.math0898.anotherpunishgui.ConfigManager;
import io.github.math0898.anotherpunishgui.structures.Punishment;
import io.github.math0898.utils.gui.GUIManager;
import io.github.math0898.utils.gui.PageableGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * PunishGUIs are specific to the player they are targeting as the punishment display, and action upon click depends on
 * the targeted player.
 *
 * @author Sugaku
 */
public class PunishGUI extends PageableGUI { // todo: On click.

    /**
     * The player this PunishGUI is specific to.
     */
    private final Player player;

    /**
     * Creates a new PageableGUI with the given name.
     *
     * @param player The player this gui is specific to.
     */
    public PunishGUI (Player player) {
        super(ChatColor.DARK_GRAY + "Punish - " + player.getName());
        this.player = player;
        GUIManager.getInstance().addGUI(getTitle(), this);
    }

    /**
     * Opens this GUI to the given player.
     *
     * @param player The player to open the GUI to.
     */
    @Override
    public void openInventory (Player player) {
        List<ItemStack> items = new ArrayList<>();
        List<Punishment> punishments = ConfigManager.getInstance().getPunishments();
        punishments.forEach((p) -> items.add(p.getItemStack(this.player)));
        setItems(items.toArray(new ItemStack[0]));
        super.openInventory(player);
    }


    /**
     * Called whenever this GUI is closed.
     *
     * @param event The inventory close event.
     */
    @Override
    public void onClose (InventoryCloseEvent event) {
        GUIManager.getInstance().removeGUI(getTitle());
    }
}
