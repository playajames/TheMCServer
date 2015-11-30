package me.playajames.tmcs.npcs;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.persistence.PlayersTable;

public class NPC {
	
	public void init(String name, Player player) {
		switch (name) {
		case "[NPC]Drug Dealer":
			new DrugDealer().openGui(player);
			break;
		case "[NPC]Food Vendor":
			new FoodVendor().openGui(player);
			break;
		default:
			player.sendMessage(GlobalData.styleChatServer + "This NPC doesn't do anything.");
		}
	}
	
	public void buy(Player player, ItemStack item, int amount) {
		PlayersTable playerClass = (PlayersTable) new PlayersTable().get(player.getUniqueId().toString(), null);
		Inventory playerInventory = player.getInventory();
		ItemStack itemClone = item.clone();
		int money = playerClass.getMoney();
		Map<String, Integer> itemMap = getItemMap(item);
		
		if (playerClass != null &&
				itemMap != null &&
					itemClone != null) {
						//Check if player has enough money
						if (money > itemMap.get("Buy") * amount) {
							//Check if player has room in inventory
							if (playerInventory.firstEmpty() != -1) {
								
								int result = money - itemMap.get("Buy") * amount;
								playerClass.setMoney(result);
								Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(playerClass);
								
								itemClone.setAmount(amount);
								playerInventory.addItem(itemClone);
								
								player.sendMessage(GlobalData.styleChatServer + "You just purchased " + amount + " " + itemClone.getItemMeta().getDisplayName() + " for " + itemMap.get("Buy") * amount + ".");
								
							} else {
								player.sendMessage(GlobalData.styleChatServer + "You don't have enough space in your inventory for that.");
								player.closeInventory();
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + "You don't have enough money for that.");
							player.closeInventory();
						}
						
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Something is wrong with this NPC, please report it to a staff member.");
			player.closeInventory();
			System.out.println("Somethings wrong, buy not triggered.");
			System.out.println(playerClass.toString());
			System.out.println(itemMap.toString());
			System.out.println(itemClone.getItemMeta().getDisplayName());
			System.out.println(money);
		}
	}
	
	public void sell(Player player, ItemStack item, int amount) {
		PlayersTable playerClass = (PlayersTable) new PlayersTable().get(player.getUniqueId().toString(), null);
		Inventory playerInventory = player.getInventory();
		ItemStack itemClone = item.clone();
		int money = playerClass.getMoney();
		Map<String, Integer> itemMap = getItemMap(item);
		
		if (playerClass != null &&
				itemMap != null &&
					itemClone != null) {
					
						if (playerInventory.containsAtLeast(itemClone, amount)) {
							int result = money + itemMap.get("Sell") * amount;
							playerClass.setMoney(result);
							Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(playerClass);
							
							itemClone.setAmount(amount);
							playerInventory.removeItem(itemClone);
							
							player.sendMessage(GlobalData.styleChatServer + "You just sold " + amount + " " + itemClone.getItemMeta().getDisplayName() + " for " + itemMap.get("Sell") * amount + ".");
						
						} else {
							player.sendMessage(GlobalData.styleChatServer + "You don't have enough of that item.");
							player.closeInventory();
						}
						
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Something is wrong with this NPC, please report it to a staff member.");
			System.out.println("Somethings wrong, sell not triggered.");
			System.out.println(playerClass.toString());
			System.out.println(itemMap.toString());
			System.out.println(itemClone.getItemMeta().getDisplayName());
			System.out.println(money);
			player.sendMessage(GlobalData.styleChatServer + "Something is wrong with this NPC, please report it to a staff member.");
		}
	}
	
	private Map<String, Integer> getItemMap(ItemStack item) {
		Map<String, Integer> itemMap = null;
		try {
			itemMap = GlobalData.customItemData.get(item.getItemMeta().getDisplayName());
		} catch (NullPointerException exception) { 
			try {
				itemMap = GlobalData.vanillaItemData.get(item.getType().toString());
			} catch(NullPointerException exception1) {
				return itemMap;
			}
		}
		return itemMap;
	}
}
