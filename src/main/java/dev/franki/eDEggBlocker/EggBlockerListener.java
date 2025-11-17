package dev.franki.eDEggBlocker;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class EggBlockerListener implements Listener {

    public EggBlockerListener(Plugin plugin) {
    }

    private boolean isEnderChestInventory(InventoryType type) {
        return type == InventoryType.ENDER_CHEST;
    }

    private boolean isDragonEgg(ItemStack item) {
        return item != null && item.getType() == Material.DRAGON_EGG;
    }

    private void notifyPlayer(@NotNull Player player) {
        player.sendActionBar("Das Drachenei darf nicht in einer Ender-Chest eingelagert werden!");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        ItemStack cursor = event.getCursor();
        ItemStack current = event.getCurrentItem();

        // Cursor Click
        if (isEnderChestInventory(event.getClickedInventory().getType())) {
            if (isDragonEgg(cursor)) {
                event.setCancelled(true);
                if (event.getWhoClicked() instanceof Player) notifyPlayer((Player) event.getWhoClicked());
                return;
            }
        }

        // Shift+Click + Hotkeys
        if (isDragonEgg(current)) {
            if (event.getView() != null) {
                Inventory topInv = event.getView().getTopInventory();
                Inventory clickedInv = event.getClickedInventory();
                if (topInv != null && isEnderChestInventory(topInv.getType())) {
                    boolean movingToTop = event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY
                            || event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD
                            || event.getAction() == InventoryAction.HOTBAR_SWAP
                            || (event.getClick() != null && event.getClick().isShiftClick());

                    if (movingToTop && clickedInv != null && clickedInv != topInv) {
                        event.setCancelled(true);
                        if (event.getWhoClicked() instanceof Player) notifyPlayer((Player) event.getWhoClicked());
                        return;
                    }
                }
            }
        }

        // Existing check: if the current item (in clicked slot) is a dragon egg and the clicked inventory itself is an ender chest
        if (isEnderChestInventory(event.getClickedInventory().getType()) && isDragonEgg(current)) {
            // Example: clicking an egg inside the ender chest (defensive) — prevent moving within
            event.setCancelled(true);
            if (event.getWhoClicked() instanceof Player) notifyPlayer((Player) event.getWhoClicked());
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (isEnderChestInventory(event.getInventory().getType())) {
            for (int rawSlot : event.getRawSlots()) {
                if (rawSlot < event.getInventory().getSize()) {
                    // Drag & Drop
                    for (ItemStack item : event.getNewItems().values()) {
                        if (isDragonEgg(item)) {
                            event.setCancelled(true);
                            if (event.getWhoClicked() instanceof Player) notifyPlayer((Player) event.getWhoClicked());
                            return;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        // Für Hopper
        if (event.getDestination().getType() == InventoryType.ENDER_CHEST) {
            if (isDragonEgg(event.getItem())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        // Keine Ahnung, Copilot meinte das wäre wichtig
        if (event.getBlockAgainst().getType() == Material.ENDER_CHEST && event.getItemInHand().getType() == Material.DRAGON_EGG) {
            event.setCancelled(true);
            notifyPlayer(event.getPlayer());
        }
    }
}
