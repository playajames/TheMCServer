package me.playajames.tmcs.listeners;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import me.playajames.tmcs.GlobalData;

public class PlayerKick implements Listener {

	@EventHandler
	public void onKick(PlayerKickEvent event) {
		OfflinePlayer player = event.getPlayer();
		//new TimePlayedHandler().calculatePlayer(player);
		GlobalData.onlinePlayers.remove(player);
	}
}
