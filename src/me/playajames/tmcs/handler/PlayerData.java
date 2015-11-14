package me.playajames.tmcs.handler;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;

public class PlayerData {
	
	/*
	 * Data Fields
	 * 	uuid
	 * 	displayName
	 * 	groupID
	 *	address
	 *	infractions
	 *	money
	 *	kills
	 *	deaths
	 *	monsterKills
	 *	firstJoinTimeStamp
	 *	lastJoinTimeStamp
	 *	lastQuitTimeStamp
	 *	data
	 */
	
	public boolean create(Player player) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("uuid", player.getUniqueId());
		map.put("displayName", player.getDisplayName());
		map.put("groupID", 1);
		map.put("address", player.getAddress());
		map.put("infractions", 0);
		map.put("money", 0);
		map.put("kills", 0);
		map.put("deaths", 0);
		map.put("monsterKills", 0);
		map.put("firstJoinTimeStamp", null);
		map.put("lastJoinTimeStamp", null);
		map.put("lastQuitTimeStamp", null);
		map.put("data", null);
		if (put(player, map)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean storePlayer(Player player) {
		return false;
	}
	
	public boolean loadPlayer(Player player) {
		return false;
	}
	
	public boolean storeAll(Player player) {
		return false;
	}	
	
	public boolean loadAll(Player player) {
		return false;
	}
	
	public String lookup(String key, Player player) {
		if (GlobalData.players.get(player.getUniqueId()) != null) {
			String str = GlobalData.players.get(player.getUniqueId()).get(key).toString();
			return str;
		} else if (null == null) {
			//Lookup offline player data
			return null;
		} else {
			return null;
		}
	}
	
	public boolean set(String key, Object value, Player player) {
		if (GlobalData.players.get(player.getUniqueId()) != null) {
			HashMap<String,Object> map = GlobalData.players.get(player.getUniqueId());
			map.put(key, value);
			GlobalData.players.put(player.getUniqueId(), map);
			return true;
		}
		return false;
	}
	
	public boolean put(Player player, HashMap<String,Object> map) {
		if (GlobalData.players.put(player.getUniqueId(), map) != null) {
			return true;
		} else {
			return false;
		}
	}
}
