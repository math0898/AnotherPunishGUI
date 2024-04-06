package io.github.math0898.anotherpunishgui.gui;

import io.github.math0898.anotherpunishgui.database.DatabaseProvider;
import io.github.math0898.anotherpunishgui.structures.Log;
import io.github.math0898.utils.gui.GUIManager;
import io.github.math0898.utils.gui.PageableGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * The History GUI opens a GUI to the player that displays all the enacted punishments on the player.
 *
 * @author Sugaku
 */
public class HistoryGUI extends PageableGUI {

    /**
     * The player we're going to load the data of.
     */
    private final Player player;

    /**
     * Creates a new PageableGUI with the given name.
     *
     * @param player The player to create this history GUI on.
     */
    public HistoryGUI (Player player) {
        super(ChatColor.DARK_GRAY + "History - " + player.getName());
        GUIManager.getInstance().addGUI(getTitle(), this);
        this.player = player;
    }

    /**
     * Opens this GUI to the given player.
     *
     * @param player The player to open the GUI to.
     */
    @Override
    public void openInventory (Player player) { // todo: Needs Notes
        List<ItemStack> items = new ArrayList<>();
        List<Log> logs = DatabaseProvider.getInstance().getDatabase().getLogs(this.player);
        logs.forEach((l) -> items.add(l.getItemStack()));
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
