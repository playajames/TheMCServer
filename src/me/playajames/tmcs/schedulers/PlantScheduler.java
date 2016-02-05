package me.playajames.tmcs.schedulers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

import me.playajames.tmcs.Main;
import me.playajames.tmcs.blocks.WeedStress;
import me.playajames.tmcs.persistence.PlantsTable;

public class PlantScheduler {
	
	public void init() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
        	
        	@Override
            public void run() {
        		@SuppressWarnings("unchecked")
				List<PlantsTable> list = (List<PlantsTable>) new PlantsTable().get();
                for (PlantsTable plantClass : list) {
                	World world = Main.getPlugin().getServer().getWorld(plantClass.getWorld());
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
            }
        }, 0L, 100L);
	}
}
