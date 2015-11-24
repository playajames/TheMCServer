package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.Logger;
import me.playajames.tmcs.handler.TimePlayed;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		new Logger().playerQuit(player);
		new TimePlayed().calculatePlayer(player);
		GlobalData.onlinePlayers.remove(player);
	}
}
