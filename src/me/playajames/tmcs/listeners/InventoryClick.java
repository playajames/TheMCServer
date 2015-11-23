package me.playajames.tmcs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.playajames.tmcs.npcs.DrugDealer;
import me.playajames.tmcs.npcs.FoodVendor;

public class InventoryClick implements Listener{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory().equals(DrugDealer.inventoryGui)) new DrugDealer().onClick(event);
		if (event.getInventory().equals(FoodVendor.inventoryGui)) new FoodVendor().onClick(event); 
	}
}
