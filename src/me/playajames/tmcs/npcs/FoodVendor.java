package me.playajames.tmcs.npcs;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FoodVendor {
	public static Inventory inventoryGui = Bukkit.createInventory(null, 9, "Food Vendor");
	public static Map<ItemStack, Map<String,Integer>> items = new HashMap<ItemStack, Map<String,Integer>>();
	
	public void openGui(Player player) {
		player.openInventory(inventoryGui);
	}
	
	public void onClick(InventoryClickEvent event) {
		
	}
}
