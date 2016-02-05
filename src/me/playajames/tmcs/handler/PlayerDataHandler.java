package me.playajames.tmcs.handler;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.playajames.tmcs.Main;
import me.playajames.tmcs.persistence.PlayersTable;

public class PlayerDataHandler {
	
	/*
	 * Data Fields
	 * 	uuid
	 * 	displayName
	 * 	groupID
	 *	address
	 *	infractions
	 *	money
	 *	license
	 *	kills
	 *	deaths
	 *	monsterKills
	 *	firstJoinTimeStamp
	 *	lastJoinTimeStamp
	 *	lastQuitTimeStamp
	 *	timePlayed
	 *	data
	 */
	
	public boolean create(Player player) {
		try {
			String uuid = player.getUniqueId().toString();
			PlayersTable playerClass = (PlayersTable) new PlayersTable().get(uuid, null);
			if (playerClass == null) {
				playerClass = new PlayersTable();
			} else {
				return false;
			}
			playerClass.setUuid(uuid);
			playerClass.setDisplayName(player.getDisplayName());
			playerClass.setGroupID(1);
			playerClass.setAddress(player.getAddress().toString());
			playerClass.setInfractions(0);
			playerClass.setMoney(0);
			playerClass.setKills(0);
			playerClass.setDeaths(0);
			playerClass.setMonsterKills(0);
			playerClass.setFirstJoinTimestamp(new Date().toString());
			playerClass.setLastJoinTimestamp(new Date().toString());
			playerClass.setLastQuitTimestamp(null);
			playerClass.setTimePlayed(0);
			playerClass.setData(null);
			Main.getPlugin().getDatabase().save(playerClass);
			Bukkit.getServer().getLogger().info("Data created for " + player.getDisplayName() + " successfully.");
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
