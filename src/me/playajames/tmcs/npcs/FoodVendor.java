package me.playajames.tmcs.npcs;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.playajames.tmcs.handler.PermissionsHandler;

public class FoodVendor {
	public static Inventory inventoryGui = Bukkit.createInventory(null, 27, "Food Vendor");
	public static Map<ItemStack, Map<String,Integer>> items = new HashMap<ItemStack, Map<String,Integer>>();
	
	static {
		inventoryGui.setItem(0, new ItemStack(Material.COOKED_BEEF));
		inventoryGui.setItem(1, new ItemStack(Material.COOKED_MUTTON));
		inventoryGui.setItem(2, new ItemStack(Material.COOKED_RABBIT));
		inventoryGui.setItem(3, new ItemStack(Material.COOKED_CHICKEN));
		inventoryGui.setItem(4, new ItemStack(Material.COOKED_FISH));
		inventoryGui.setItem(5, new ItemStack(Material.RAW_BEEF));
		inventoryGui.setItem(6, new ItemStack(Material.RAW_CHICKEN));
		inventoryGui.setItem(7, new ItemStack(Material.RAW_FISH));
		inventoryGui.setItem(8, new ItemStack(Material.CAKE));
		inventoryGui.setItem(9, new ItemStack(Material.RABBIT_STEW));
		inventoryGui.setItem(10, new ItemStack(Material.PUMPKIN_PIE));
		inventoryGui.setItem(11, new ItemStack(Material.MUSHROOM_SOUP));
		inventoryGui.setItem(12, new ItemStack(Material.BREAD));
		inventoryGui.setItem(13, new ItemStack(Material.COOKIE));
		inventoryGui.setItem(14, new ItemStack(Material.BAKED_POTATO));
		inventoryGui.setItem(15, new ItemStack(Material.APPLE));
		inventoryGui.setItem(16, new ItemStack(Material.CARROT_ITEM));
		inventoryGui.setItem(17, new ItemStack(Material.MELON));
		inventoryGui.setItem(18, new ItemStack(Material.POTATO_ITEM));
		inventoryGui.setItem(19, new ItemStack(Material.GOLDEN_APPLE));
	}
	
	public void openGui(Player player) {
		if (player.hasPermission("tmcs.npc.foodvendor")) {
			player.openInventory(inventoryGui);
		} else {
			new PermissionsHandler().denyTask(player, "access npc", "Food Vendor");
		}
	}
	
	public void onClick(InventoryClickEvent event) {
		event.setCancelled(true);
	}
}
