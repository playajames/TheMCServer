package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.LoggerHandler;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		new LoggerHandler().playerQuit(player);
		//new TimePlayedHandler().calculatePlayer(player);
		GlobalData.onlinePlayers.remove(player);
	}
}
