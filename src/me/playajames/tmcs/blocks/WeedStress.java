package me.playajames.tmcs.blocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.playajames.tmcs.handler.Permissions;
import me.playajames.tmcs.handler.TimePlayed;
import me.playajames.tmcs.items.ItemWeedStress;
import me.playajames.tmcs.items.ItemWeedStressSeed;
import me.playajames.tmcs.persistence.Plants;

public final class WeedStress {
	
	/*
	 *  Grow time:
	 */
	private double growRate = 0.5;
	private int id = 9;
	
	public int getId() {
		return this.id;
	}
	
	public ItemStack getSeed() {
		return new ItemWeedStressSeed().getItem();
	}
	
	public ItemStack getCrop() {
		return new ItemWeedStress().getItem();
	}
	
	public boolean check(BlockPlaceEvent event) {
		ItemMeta itemMeta = event.getPlayer().getItemInHand().getItemMeta();
		if (itemMeta.getDisplayName().equals("Weed Seed")) {
			if (itemMeta.getLore().contains(ChatColor.YELLOW + "Type: Stress")) {
				plant(event.getPlayer(), event.getBlock());
				return true;
			}
		}
		return false;
	}
	
	public boolean harvestCheck(BlockBreakEvent event) {
		Block block = event.getBlock();
		if (new Plants().contains(block.getLocation())) {
			event.setCancelled(true);
			Plants plantClass = (Plants) new Plants().get(block.getLocation(), null);
			harvest(block, plantClass);
			//if (GlobalData.debug) event.getPlayer().sendMessage("[Debug] Weed plant broke.");
			return true;
		}
		//if (GlobalData.debug) event.getPlayer().sendMessage("[Debug] Crop plant not found in database. Crop could be from vanilla MC seeds.");
		return false;
	}
	
	public void harvest(Block block, Plants plantClass) {
		block.setType(Material.AIR);
		List<ItemStack> drops = getDrops(plantClass);
		if (drops.size() >= 1) {
			for (ItemStack drop : drops) {
				block.getLocation().getWorld().dropItemNaturally(block.getLocation(), drop);
			}
		}
		try {
			Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().delete(plantClass);
		} catch (Exception exception){
			System.out.println("There was a problem deleting plant [id=" + plantClass.getId() + "].");
			exception.printStackTrace();
		}
	}
	
	public void plant(Player player, Block block) {
		Date now = new Date();
		if (player.hasPermission("tmcs.plant.weedseedstress")) {
			Plants plantClass = new Plants();
			plantClass.setWorld(block.getWorld().getName());
			plantClass.setLocX(block.getX());
			plantClass.setLocY(block.getY());
			plantClass.setLocZ(block.getZ());
			plantClass.setType(9);
			plantClass.setHealth(100);
			plantClass.setGrowRate(growRate);
			plantClass.setStage(0);
			plantClass.setWaterLevel(0);
			plantClass.setNutrients(0);
			plantClass.setPlantedTime(now.toString());
			plantClass.setLastCheckTime(now.toString());
			Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(plantClass);
			block.setType(Material.CROPS);
			//if (GlobalData.debug) player.sendMessage("[Debug] Weed plant placed.");
		} else {
			new Permissions().denyTask(player, "plant", "[Item:WeedSeedStress]");
		}
	}
	
	public boolean canGrow(Block block, Plants plantClass) {
		if (plantClass.getStage() < 7) {
			//if (GlobalData.debug) System.out.println("Plant can grow.");
			grow(block, plantClass);
			return true;
		}
		return false;
	}
	
	public boolean getGrowthChance(Block block, World world, Plants plantClass) {
		//
		return false;
	}
	
	public List<ItemStack> getDrops(Plants plantClass) {
		List<ItemStack> drops = new ArrayList<ItemStack>();
		int random = new Random().nextInt(100) + 1;
		ItemStack itemSeed = new ItemWeedStressSeed().getItem();
		ItemStack itemCrop = new ItemWeedStress().getItem();
		switch (plantClass.getStage()) {
		case 7: 
			if (random > 80) {
				itemCrop.setAmount(3);
				itemSeed.setAmount(2);
				drops.add(itemSeed);
				drops.add(itemCrop);
			} else if (random > 60) {
				itemCrop.setAmount(2);
				itemSeed.setAmount(1);
				drops.add(itemSeed);
				drops.add(itemCrop);
			} else if (random > 40) {
				itemCrop.setAmount(1);
				itemSeed.setAmount(1);
				drops.add(itemSeed);
				drops.add(itemCrop);
			} else {
				itemCrop.setAmount(1);
				drops.add(itemCrop);
			}
			break;
		case 6: 
			if (random > 80) {
				itemCrop.setAmount(2);
				itemSeed.setAmount(1);
				drops.add(itemSeed);
				drops.add(itemCrop);
			} else if (random > 60) {
				itemCrop.setAmount(1);
				itemSeed.setAmount(1);
				drops.add(itemSeed);
				drops.add(itemCrop);
			} else if (random > 50) {
				itemCrop.setAmount(1);
				drops.add(itemCrop);
			} else if (random > 20) {
				drops.add(itemSeed);
			}
			break;
		case 5:
			if (random > 60) {
				itemSeed.setAmount(1);
				drops.add(itemSeed);
			}
		case 4:
			if (random > 50) {
				itemSeed.setAmount(1);
				drops.add(itemSeed);
			}
		case 3:
			if (random > 40) {
				itemSeed.setAmount(1);
				drops.add(itemSeed);
			}
		case 2:
			if (random > 30) {
				itemSeed.setAmount(1);
				drops.add(itemSeed);
			}
		case 1:
			if (random > 20) {
				itemSeed.setAmount(1);
				drops.add(itemSeed);
			}
		case 0:
			if (random > 20) {
				itemSeed.setAmount(1);
				drops.add(itemSeed);
			}
		default:
			break;
				
		}
		//if (GlobalData.debug) System.out.println("Random Number: " + random);
		return drops;
	}
	
	@SuppressWarnings("deprecation")
	public void grow(Block block, Plants plantClass) {
		//int waterLevel = plantClass.getWaterLevel();
		//int health = plantClass.getHealth();
		//int nutrients = plantClass.getNutrients();
		int stage = plantClass.getStage();
		double growRate = plantClass.getGrowRate();
		int light = block.getLightLevel();
		String lastCheckTime = plantClass.getLastCheckTime();
		Date now = new Date();
		//Date plantedTime = new TimePlayed().formatDate(plantClass.getPlantedTime());
		Date lastCheckTimeDate = new TimePlayed().formatDate(lastCheckTime);
		long diff = now.getTime() - lastCheckTimeDate.getTime();
		long diffSeconds = diff / 1000;
		if (block.getType().equals(Material.CROPS)) {
			if (diffSeconds >= 60 * growRate) {
				if (light >= 9) {
					stage++;
					block.setData(Byte.parseByte(String.valueOf(stage)));
					plantClass.setStage(stage);
					plantClass.setLastCheckTime(now.toString());
					Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(plantClass);
				}
			}
		} else {
			
		}
		return;
	}
	
	public boolean canUseBonemeal() {
		return false;
	}
	
	public boolean checkPlantedOn(Block block) {
		if (new Plants().contains(block.getRelative(BlockFace.UP, 1).getLocation())) {
			return true;
		}
		return false;
	}
}
