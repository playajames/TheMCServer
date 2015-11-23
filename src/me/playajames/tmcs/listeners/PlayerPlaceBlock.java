package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.playajames.tmcs.blocks.WeedStress;
import me.playajames.tmcs.handler.Logger;

public class PlayerPlaceBlock implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (event.getPlayer() instanceof Player) {
			new Logger().blockPlace(event.getPlayer(), event.getBlock());
			
			// Check if item is custom
			if (event.getItemInHand().getItemMeta().hasDisplayName()) {
				new WeedStress().check(event);
			}
			
		}
	}
}
