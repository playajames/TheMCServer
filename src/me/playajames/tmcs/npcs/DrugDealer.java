package me.playajames.tmcs.npcs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.playajames.tmcs.handler.PermissionsHandler;
import me.playajames.tmcs.items.ItemWeedStress;
import me.playajames.tmcs.items.ItemWeedStressSeed;

public class DrugDealer {
	
	public static Inventory inventoryGui = Bukkit.createInventory(null, 9, "Drug Dealer");
	private int size = 2;
	
	public void openGui(Player player) {
		if (player.hasPermission("tmcs.npc.drugdealer")) {
			inventoryGui.setItem(0, new ItemWeedStressSeed().getItem());
			inventoryGui.setItem(1, new ItemWeedStress().getItem());
			player.openInventory(inventoryGui);
		} else {
			new PermissionsHandler().denyTask(player, "access npc", "Drug Dealer");
		}
	}
	
	public void onClick(InventoryClickEvent event) {
		event.setCancelled(true);
		if (event.getSlotType().equals(SlotType.CONTAINER) && 
				event.getSlot() <= size - 1 &&
					event.getWhoClicked() instanceof Player) {
						
						Player player = (Player) event.getWhoClicked();
						ItemStack item = event.getCurrentItem().clone();
						
						// Buy check
						if (event.isLeftClick() && event.isShiftClick()) {
							new NPC().buy(player, item, 64);
						} else if (event.isLeftClick()) {
							new NPC().buy(player, item, 1);
						}
						
						// Sell check
						if (event.isRightClick() && event.isShiftClick()) {
							new NPC().sell(player, item, 64);
						} else if (event.isRightClick()) {
							new NPC().sell(player, item, 1);
						}
						
		}
	}
}
