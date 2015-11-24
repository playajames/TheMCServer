package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.TimePlayed;

public class PlayerKick implements Listener {

	@EventHandler
	public void onKick(PlayerKickEvent event) {
		Player player = event.getPlayer();
		new TimePlayed().calculatePlayer(player);
		GlobalData.onlinePlayers.remove(player);
	}
}
