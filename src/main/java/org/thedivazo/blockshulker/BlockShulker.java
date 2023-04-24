package org.thedivazo.blockshulker;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockShulker extends JavaPlugin implements Listener {

    private boolean isShulkerBox(Material material) {
        switch (material) {
            case MAGENTA_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("Плагин начал работу");
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(isShulkerBox(event.getBlockPlaced().getType()) && event.getBlockPlaced().getLocation().getBlockY() <= 5 && !event.getPlayer().isOp()) {
            event.getPlayer().sendMessage(ChatColor.RED + "Вы не можете поставить здесь шалкер");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.CHEST && event.getCurrentItem() != null && isShulkerBox(event.getCurrentItem().getType()) && !event.getWhoClicked().isOp()) {
            event.getWhoClicked().sendMessage(ChatColor.RED + "Вы не можете поместить шалкер в сундук");
            event.setCancelled(true);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин закончил работу");
        HandlerList.unregisterAll((Listener) this);
    }
}
