package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.playajames.tmcs.npcs.NPC;

public class PlayerInteractEntity implements Listener {
	
	@EventHandler 
	public void onEntityClick(PlayerInteractEntityEvent event){ 
		Player player = event.getPlayer();
		if (event.getRightClicked() instanceof Player) {
			Player rightClicked = (Player) event.getRightClicked();
			if (rightClicked.hasMetadata("NPC")) {
				new NPC().init(rightClicked.getDisplayName(), player);
			}
		}
	}
}
