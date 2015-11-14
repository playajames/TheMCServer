package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.playajames.tmcs.handler.Logger;

public class EntityDeath implements Listener{	
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player) {
			new Logger().playerKillEntity(event.getEntity().getKiller(), event.getEntity());
		}
	}
}
