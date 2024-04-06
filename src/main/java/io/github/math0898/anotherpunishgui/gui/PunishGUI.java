package io.github.math0898.anotherpunishgui.gui;

import io.github.math0898.anotherpunishgui.ConfigManager;
import io.github.math0898.anotherpunishgui.punisher.Punisher;
import io.github.math0898.anotherpunishgui.punisher.PunisherProvider;
import io.github.math0898.anotherpunishgui.structures.Punishment;
import io.github.math0898.utils.gui.GUIManager;
import io.github.math0898.utils.gui.PageableGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * PunishGUIs are specific to the player they are targeting as the punishment display, and action upon click depends on
 * the targeted player.
 *
 * @author Sugaku
 */
public class PunishGUI extends PageableGUI { // todo: On click, shouldn't extend pageable.

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
        Inventory inv = Bukkit.createInventory(player, 45, getTitle());
        List<ItemStack> items = new ArrayList<>();
        List<Punishment> punishments = ConfigManager.getInstance().getPunishments();
        punishments.forEach((p) -> items.add(p.getItemStack(this.player)));
        items.forEach(inv::addItem);
        player.openInventory(inv);
    }

    /**
     * Called whenever this GUI is clicked.
     *
     * @param event The inventory click event.
     */
    @Override
    public void onClick (InventoryClickEvent event) {
        List<Punishment> punishments = ConfigManager.getInstance().getPunishments();
        if (punishments.size() > event.getSlot()) {
            Punisher punisher = PunisherProvider.getInstance().getPunisher();
            punisher.punish(punishments.get(event.getSlot()), event.getWhoClicked(), this.player);
        }
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
