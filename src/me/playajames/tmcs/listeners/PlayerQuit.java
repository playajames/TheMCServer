package me.playajames.tmcs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.playajames.tmcs.handler.Logger;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		new Logger().playerQuit(event.getPlayer());
	}
}
