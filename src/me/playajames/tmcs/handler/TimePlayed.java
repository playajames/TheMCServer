package me.playajames.tmcs.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.playajames.tmcs.persistence.Players;

public class TimePlayed {

	public void calculate(Player player) {
		try {
			Players playerClass = (Players) new Players().get(player.getUniqueId().toString(), null);
			String lastJoinString = playerClass.getLastJoinTimestamp();
			Long timePlayed = playerClass.getTimePlayed();
			Date now = new Date();
			Date lastJoin = new Date();
			lastJoin = formatDate(lastJoinString);
			long diff = now.getTime() - lastJoin.getTime();
			long diffSeconds = diff / 1000;
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
	
	public Date formatDate(String value) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		try {
			return formatter.parse(value);
		} catch (Exception e) {
			System.out.println("There was a problem parsing " + value + " to a date.");
			e.printStackTrace();
			return null;
		}
	}
	
}
