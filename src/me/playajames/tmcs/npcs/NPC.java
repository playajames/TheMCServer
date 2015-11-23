package me.playajames.tmcs.npcs;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.Permissions;
import me.playajames.tmcs.items.CustomItems;
import me.playajames.tmcs.persistence.Players;

public class NPC {
	
	public void init(String name, Player player) {
		switch (name) {
		case "[NPC]Drug Dealer":
			if (player.hasPermission("tmcs.npc.drugdealer")) {
				new DrugDealer().openGui(player);
			} else {
				new Permissions().denyTask(player, "access NPC", "Drug Dealer");
			}
			break;
		case "[NPC]Food Vendor":
			if (player.hasPermission("tmcs.npc.foodvendor")) {
				new FoodVendor().openGui(player);
			} else {
				new Permissions().denyTask(player, "access NPC", "Food Vendor");
			}
			break;
		default:
			player.sendMessage(GlobalData.styleChatServer + "This NPC doesn't do anything.");
		}
	}
	
	public void buy(Player player, ItemStack item, int amount) {
		Players playerClass = (Players) new Players().get(player.getUniqueId().toString(), null);
		Map<String, Integer> valueMap = CustomItems.data.get(item);
		Inventory playerInventory = player.getInventory();
		ItemStack itemClone = item.clone();
		int money = playerClass.getMoney();
		
		if (playerClass != null &&
				valueMap != null &&
					itemClone != null) {
						//Check if player has enough money
						if (money > valueMap.get("buy") * amount) {
							//Check if player has room in inventory
							if (playerInventory.firstEmpty() != -1) {
								
								int result = money - valueMap.get("buy") * amount;
								playerClass.setMoney(result);
								Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(playerClass);
								
								itemClone.setAmount(amount);
								playerInventory.addItem(itemClone);
								
								player.sendMessage(GlobalData.styleChatServer + "You just purchased " + amount + " " + itemClone.getItemMeta().getDisplayName() + " for " + valueMap.get("buy") * amount + ".");
								
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
			System.out.println("Somethings wrong, buy not triggered.");
			System.out.println(playerClass.toString());
			System.out.println(valueMap.toString());
			System.out.println(itemClone.getItemMeta().getDisplayName());
			System.out.println(money);
		}
	}
	
	public void sell(Player player, ItemStack item, int amount) {
		Players playerClass = (Players) new Players().get(player.getUniqueId().toString(), null);
		Map<String, Integer> valueMap = CustomItems.data.get(item);
		Inventory playerInventory = player.getInventory();
		ItemStack itemClone = item.clone();
		int money = playerClass.getMoney();
		
		if (playerClass != null &&
				valueMap != null &&
					itemClone != null) {
					
						if (playerInventory.containsAtLeast(itemClone, amount)) {
							int result = money + valueMap.get("sell") * amount;
							playerClass.setMoney(result);
							Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(playerClass);
							
							itemClone.setAmount(amount);
							playerInventory.removeItem(itemClone);
							
							player.sendMessage(GlobalData.styleChatServer + "You just sold " + amount + " " + itemClone.getItemMeta().getDisplayName() + " for " + valueMap.get("sell") * amount + ".");
						
						} else {
							player.sendMessage(GlobalData.styleChatServer + "You don't have enough of that item.");
							player.closeInventory();
						}
						
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Something is wrong with this NPC, please report it to a staff member.");
			System.out.println("Somethings wrong, sell not triggered.");
			System.out.println(playerClass.toString());
			System.out.println(valueMap.toString());
			System.out.println(itemClone.getItemMeta().getDisplayName());
			System.out.println(money);
			player.sendMessage(GlobalData.styleChatServer + "Something is wrong with this NPC, please report it to a staff member.");
		}
	}
}
