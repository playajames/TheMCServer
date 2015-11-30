package me.playajames.tmcs.listeners;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.LoggerHandler;
import me.playajames.tmcs.handler.PlayerDataHandler;
import me.playajames.tmcs.persistence.PlayersTable;

public class PlayerJoin implements Listener {

	///// On Join
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Date now = new Date();
		GlobalData.onlinePlayers.add(player);
		new LoggerHandler().playerJoin(player);
		PlayersTable playerClass = (PlayersTable) new PlayersTable().get(player.getUniqueId().toString(), null);
		if (playerClass != null) {
			player.sendMessage(GlobalData.styleChatServer + "Welcome back, " + player.getDisplayName() + ".");
			playerClass.setLastJoinTimestamp(now.toString());
			Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(playerClass);
		} else {
			if (new PlayerDataHandler().create(player)) {
				player.sendMessage(GlobalData.styleChatServer + "Welcome to the server, " + player.getDisplayName() + "!");
			} else {
				player.kickPlayer(GlobalData.styleChatServer + ChatColor.RED + "There was a problem creating your character, please contact a server administrator.");
			}
		}
	}
}
