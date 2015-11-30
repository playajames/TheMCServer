package me.playajames.tmcs.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

import me.playajames.tmcs.persistence.PlantsTable;

public class BlockGrow implements Listener {

	@EventHandler
	public void onBlockGrow(BlockGrowEvent event) {
		if (event.getBlock().getType().equals(Material.CROPS)) {
			if (new PlantsTable().contains(event.getBlock().getLocation())) event.setCancelled(true);
			//if (GlobalData.debug) System.out.println("[Debug] Grow event canceled, plant was found in plants database.");
			//if (GlobalData.debug) System.out.println("[Debug] Grow block event triggered.");
		}
	}
	
}
