package io.github.math0898.utils.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The ItemBuilder is made to easily manufacture ItemStacks.
 *
 * @author Sugaku
 */
public class ItemBuilder {

    /**
     * The material that the resulting item will have.
     */
    private Material material;

    /**
     * The display name that the resulting item will have.
     */
    private String displayName = null;

    /**
     * The Lore that the resulting item will have.
     */
    private List<String> lore = null;

    /**
     * The uuid of the owning player. Ignored if not a player head.
     */
    private UUID owningPlayer = null;

    /**
     * Creates a new ItemBuilder using the given material.
     *
     * @param material The material to use for this ItemBuilder.
     */
    public ItemBuilder(Material material) {
        this.material = material;
    }

    /**
     * Called to set the material used for this ItemBuilder. Returns the ItemBuilder to allow for chain calling.
     *
     * @param material The material to set this ItemBuilder to.
     * @return The mutated ItemBuilder.
     */
    public ItemBuilder setMaterial (Material material) {
        this.material = material;
        return this;
    }

    /**
     * Called to set the display name used for this ItemBuilder. Returns the ItemBuilder to allow for chain calling.
     *
     * @param name The display name for the resulting item.
     * @return The mutated ItemBuilder.
     */
    public ItemBuilder setDisplayName (String name) {
        this.displayName = name;
        return this;
    }

    /**
     * Called to set the lore value used for this ItemBuilder. Returns the ItemBuilder to allow for chain calling.
     *
     * @param lore The lore for the resulting item.
     * @return The mutated ItemBuilder.
     */
    public ItemBuilder setLore (String[] lore) {
        this.lore = new ArrayList<>(List.of(lore));
        return this;
    }

    /**
     * Used to set the owning player for skulls. Otherwise, ignored.
     *
     * @param uuid The uuid of the owning player.
     * @return The mutated ItemBuilder.
     */
    public ItemBuilder setOwningPlayer (UUID uuid) {
        this.owningPlayer = uuid;
        return this;
    }

    /**
     * Called to get the resulting ItemStack object.
     *
     * @return The builder's resulting item.
     */
    public ItemStack build () {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) Bukkit.getItemFactory().getItemMeta(material);
        assert meta != null;
        if (displayName != null) meta.setDisplayName(displayName);
        if (lore != null) meta.setLore(lore);
        if (owningPlayer != null && meta instanceof SkullMeta skull)
            skull.setOwningPlayer(Bukkit.getOfflinePlayer(owningPlayer));
        item.setItemMeta(meta);
        return item;
    }
}
