package me.playajames.tmcs.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemWeedStressSeed {
	public static ItemStack item = new ItemStack(Material.SEEDS);
	public int buyValue = 9;
	public int sellValue = 3;
	
	public void register() {
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		itemMeta.setDisplayName("Weed Seed");
		
		lore.add(ChatColor.RED + "Illegal Item");
		lore.add(ChatColor.YELLOW + "Type: Stress");
		itemMeta.setLore(lore);
		
		item.setItemMeta(itemMeta);
		
		map.put("buy", this.buyValue);
		map.put("sell", this.sellValue);
		CustomItems.data.put(item, map);
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public void setItem(ItemStack item) {
		ItemWeedStressSeed.item = item;
	}
	
	public int getBuyValue() {
		return this.buyValue;
	}
	
	public int getSellValue() {
		return this.sellValue;
	}
}
