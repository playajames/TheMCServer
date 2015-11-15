package me.playajames.tmcs.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.Logger;
import me.playajames.tmcs.handler.PlayerData;
import me.playajames.tmcs.persistence.Players;

public class PlayerJoin implements Listener {

	///// On Join
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		new Logger().playerJoin(player);
		if (new Players().get(player.getUniqueId().toString(), null) != null) {
			player.sendMessage(GlobalData.styleChatServer + "Welcome back, " + player.getDisplayName() + ".");
		} else {
			if (new PlayerData().create(player)) {
				player.sendMessage(GlobalData.styleChatServer + "Welcome to the server, " + player.getDisplayName() + "!");
			} else {
				player.kickPlayer(GlobalData.styleChatServer + ChatColor.RED + "There was a problem creating your character, please contact a server administrator.");
			}
		}
	}
}
