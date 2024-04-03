package io.github.math0898.utils.gui;

import org.bukkit.event.Listener;

import java.util.HashMap;

/**
 * The GUIManager is used to register GUIs, open them by string name, and to forward click events from one event
 * listener.
 *
 * @author Sugaku
 */
public class GUIManager implements Listener {

    HashMap<String, GUI> guisByID;
    HashMap<String, GUI> guisByTitle;

    /**
     * Called whenever an inventory is clicked.
     */
}
