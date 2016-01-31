package me.playajames.tmcs.blocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

import me.playajames.tmcs.Main;
import me.playajames.tmcs.handler.PermissionsHandler;
import me.playajames.tmcs.handler.TimePlayedHandler;
import me.playajames.tmcs.items.ItemWeedStress;
import me.playajames.tmcs.items.ItemWeedStressSeed;
import me.playajames.tmcs.persistence.PlantsTable;

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
		Player player = event.getPlayer();
		ItemMeta itemMeta = event.getPlayer().getItemInHand().getItemMeta();
		if (itemMeta.getDisplayName().equals("Weed Seed")) {
			if (itemMeta.getLore().contains(ChatColor.YELLOW + "Type: Stress")) {
				if (player.hasPermission("tmcs.plant.weedseedstress")) {
					plant(event.getPlayer(), event.getBlock());
					return true;
				} else {
					event.setCancelled(true);
					new PermissionsHandler().denyTask(player, "plant", "[Item:WeedSeedStress]");
				}
			}
		}
		return false;
	}
	
	public boolean harvestCheck(BlockBreakEvent event) {
		Block block = event.getBlock();
		if (new PlantsTable().contains(block.getLocation())) {
			event.setCancelled(true);
			PlantsTable plantClass = (PlantsTable) new PlantsTable().get(block.getLocation(), null);
			harvest(block, plantClass);
			return true;
		}
		return false;
	}
	
	public void harvest(Block block, PlantsTable plantClass) {
		block.setType(Material.AIR);
		List<ItemStack> drops = getDrops(plantClass);
		if (drops.size() >= 1) {
			for (ItemStack drop : drops) {
				block.getLocation().getWorld().dropItemNaturally(block.getLocation(), drop);
			}
		}
		try {
			Main.getPlugin().getDatabase().delete(plantClass);
		} catch (Exception exception){
			System.out.println("There was a problem deleting plant [id=" + plantClass.getId() + "].");
			exception.printStackTrace();
		}
	}
	
	public void plant(Player player, Block block) {
		Date now = new Date();
		PlantsTable plantClass = new PlantsTable();
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
		Main.getPlugin().getDatabase().save(plantClass);
		block.setType(Material.CROPS);
	}
	
	public boolean canGrow(Block block, PlantsTable plantClass) {
		if (plantClass.getStage() < 7) {
			grow(block, plantClass);
			return true;
		}
		return false;
	}
	
	public boolean getGrowthChance(Block block, World world, PlantsTable plantClass) {
		//
		return false;
	}
	
	public List<ItemStack> getDrops(PlantsTable plantClass) {
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
	public void grow(Block block, PlantsTable plantClass) {
		int stage = plantClass.getStage();
		double growRate = plantClass.getGrowRate();
		int light = block.getLightLevel();
		String lastCheckTime = plantClass.getLastCheckTime();
		
		Date now = new Date();
		Date lastCheckTimeDate = new TimePlayedHandler().formatDate(lastCheckTime);
		long diff = now.getTime() - lastCheckTimeDate.getTime();
		long diffSeconds = diff / 1000;
		
		if (block.getType().equals(Material.CROPS)) {
			if (diffSeconds >= 60 * growRate) {
				if (light >= 9) {
					stage++;
					block.setData(Byte.parseByte(String.valueOf(stage)));
					plantClass.setStage(stage);
					plantClass.setLastCheckTime(now.toString());
					Main.getPlugin().getDatabase().save(plantClass);
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
		if (new PlantsTable().contains(block.getRelative(BlockFace.UP, 1).getLocation())) {
			return true;
		}
		return false;
	}
}
