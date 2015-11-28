package me.playajames.tmcs.schedulers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

import me.playajames.tmcs.Main;
import me.playajames.tmcs.blocks.WeedStress;
import me.playajames.tmcs.persistence.Plants;

public class PlantsScheduler {

	private Main plugin;
	public PlantsScheduler (Main plugin) {
        this.plugin = plugin;
    }
	
	public void init() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
        	
        	@Override
            public void run() {
                //if (GlobalData.debug) plugin.getLogger().info("Running plant scheduler...");
                List<Plants> list = Main.getPlugin().getDatabase().find(Plants.class).findList();
                for (Plants plantClass : list) {
                	World world = plugin.getServer().getWorld(plantClass.getWorld());
                	Location loc = new Location(world, plantClass.getLocX(), plantClass.getLocY(), plantClass.getLocZ());
                	if (loc.getBlock().getType().equals(Material.CROPS)) {
	                	switch (plantClass.getType()) {
	                	case 9:
	                		new WeedStress().canGrow(loc.getBlock(), plantClass);
	                		break;
	                	}
                	} else {
                		try {
                			Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().delete(plantClass);
                		} catch (Exception exception){
                			System.out.println("There was a problem deleting plant [id=" + plantClass.getId() + "].");
                			exception.printStackTrace();
                		}
                	}
                }
                //System.out.println(String.valueOf("Result Lists Size: " + list.size()));
                //if (GlobalData.debug) plugin.getLogger().info("Plant scheduler finished.");
            }
        }, 0L, 100L);
	}	
}
