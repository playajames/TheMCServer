package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.playajames.tmcs.handler.ClansHandler;

public class EntityDamageByEntity implements Listener{	
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			Player player = (Player) event.getDamager();
			Player targetPlayer = (Player) event.getEntity();
			event.setCancelled(new ClansHandler().pvpCheck(player, targetPlayer));
		}
	}
}
