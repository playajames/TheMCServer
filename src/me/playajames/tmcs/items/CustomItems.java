package me.playajames.tmcs.items;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

public class CustomItems {
		
	public static Map<ItemStack, Map<String, Integer>> data = new HashMap<ItemStack, Map<String, Integer>>();
	
	public void registerItems() {
		new ItemWeedStressSeed().register();
		new ItemWeedStress().register();
	}
}
