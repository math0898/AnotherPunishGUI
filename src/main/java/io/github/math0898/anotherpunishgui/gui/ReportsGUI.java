package io.github.math0898.anotherpunishgui.gui;

import io.github.math0898.anotherpunishgui.database.DatabaseProvider;
import io.github.math0898.anotherpunishgui.structures.Report;
import io.github.math0898.utils.gui.PageableGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * The ReportsGUI shows a staff member a full list of reports that have been given out.
 *
 * @author Sugaku
 */
public class ReportsGUI extends PageableGUI {

    /**
     * Creates a new PageableGUI with the given name.
     */
    public ReportsGUI () {
        super(ChatColor.DARK_GRAY + "Reports");
    }

    /**
     * Opens this GUI to the given player.
     *
     * @param player The player to open the GUI to.
     */
    @Override
    public void openInventory (Player player) {
        List<Report> reports = DatabaseProvider.getInstance().getDatabase().getReports();
        List<ItemStack> itemDisplay = new ArrayList<>();
        reports.forEach((r) -> itemDisplay.add(r.getItemStack()));
        setItems(itemDisplay.toArray(new ItemStack[0]));
        super.openInventory(player);
    }

    /**
     * Called whenever this GUI is clicked.
     *
     * @param event The inventory click event.
     */
    @Override
    public void onClick (InventoryClickEvent event) {
        super.onClick(event);
        // todo: Add a way to mark reports as resolved.
    }
}
