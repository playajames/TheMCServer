package me.playajames.tmcs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.Logger;
import me.playajames.tmcs.handler.PlayerData;

public class PlayerJoin implements Listener {

	///// On Join
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		new Logger().playerJoin(player);
		if (new PlayerData().lookup("uuid", player) != null) {
			player.sendMessage(GlobalData.styleChatServer + "Welcome back to the server, " + player.getDisplayName() + ".");
		} else {
			new PlayerData().create(player);
			player.sendMessage(GlobalData.styleChatServer + "Welcome to the server, " + player.getDisplayName() + "!");
		}
	}
}
