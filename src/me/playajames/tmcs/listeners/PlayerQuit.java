package me.playajames.tmcs.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.playajames.tmcs.handler.Logger;
import me.playajames.tmcs.persistence.Players;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		new Logger().playerQuit(event.getPlayer());
		calculateTimePlayed(event.getPlayer());
	}
	
	public void calculateTimePlayed(Player player) {
		try {
			Players playerClass = (Players) new Players().get(player.getUniqueId().toString(), null);
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			String lastJoinString = playerClass.getLastJoinTimestamp();
			Long timePlayed = playerClass.getTimePlayed();
			Date now = new Date();
			Date lastJoin = null;
			lastJoin = formatter.parse(lastJoinString);
			long diff = now.getTime() - lastJoin.getTime();
			long diffSeconds = diff / 1000 % 60;
			long result = diffSeconds + timePlayed;
			playerClass.setTimePlayed(result);
			playerClass.setLastQuitTimestamp(now.toString());
			Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(playerClass);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to calculate time played for " + player.getDisplayName() + ".");
			return;
		}
	}
}
