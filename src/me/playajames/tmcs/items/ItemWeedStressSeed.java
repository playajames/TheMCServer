package me.playajames.tmcs.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.playajames.tmcs.GlobalData;

public class ItemWeedStressSeed {
	public static ItemStack item = new ItemStack(Material.SEEDS);
	
	public void register() {
		
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		Map<String, Integer> itemMap = new HashMap<String,Integer>();
		
		itemMeta.setDisplayName("Weed Seed"); 
		lore.add(ChatColor.RED + "Illegal Item");
		lore.add(ChatColor.YELLOW + "Type: Stress");
		itemMeta.setLore(lore);
		
		itemMap.put("Buy", 11);
		itemMap.put("Sell", 6);
		
		setItemMeta(itemMeta);
		setItemMap(itemMeta.getDisplayName(), itemMap);
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	private void setItemMap(String key, Map<String, Integer> value) {
		GlobalData.customItemData.put(key, value);
	}
	
	private void setItemMeta(ItemMeta itemMeta) {
		item.setItemMeta(itemMeta);
	}
}
