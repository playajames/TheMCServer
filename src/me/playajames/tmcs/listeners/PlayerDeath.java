package me.playajames.tmcs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.playajames.tmcs.handler.Logger;

public class PlayerDeath implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		new Logger().playerDeath(event.getEntity().getPlayer(), event.getEntity().getKiller());
	}
}
