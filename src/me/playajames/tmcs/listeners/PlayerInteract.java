package me.playajames.tmcs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.playajames.tmcs.blocks.WeedStress;

public class PlayerInteract implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if (event.getAction() == Action.PHYSICAL) {
			if (new WeedStress().checkPlantedOn(event.getClickedBlock())) event.setCancelled(true);
		}
		
	}
}
