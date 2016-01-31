package me.playajames.tmcs.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LoggerHandler {

	public static ArrayList<String> cache = new ArrayList<String>();
	
	public boolean init() {
		//Repeat schedule this.
		//Push ArrayList to database and empty the list.
		return false;
	}
	
	public boolean blockBreak(Player player, Block block) {
		String UUID = player.getUniqueId().toString();
		String displayName = player.getDisplayName();
		String action = "blockBreak";
		String data = block.getType().toString();
		String address = player.getAddress().toString();
		cache.add("INSERT INTO `logger`(`uuid`, `displayName`, `action`, `data`, `address`) VALUES ("+UUID+","+displayName+","+action+","+data+","+address+")");
		return false;
	}
	
	public boolean blockPlace(Player player, Block block) {
		String UUID = player.getUniqueId().toString();
		String displayName = player.getDisplayName();
		String action = "blockPlace";
		String data = block.getType().toString();
		String address = player.getAddress().toString();
		cache.add("INSERT INTO `logger`(`uuid`, `displayName`, `action`, `data`, `address`) VALUES ("+UUID+","+displayName+","+action+","+data+","+address+")");
		return false;
	}
	
	public boolean playerDeath(Player player, Player killer) {
		String UUID = player.getUniqueId().toString();
		String displayName = player.getDisplayName();
		String action = "playerDeath";
		String data = null;
// ** Need to check for monster killed player.
		if (killer instanceof Player) { 
			data = killer.getDisplayName(); 
		}
		String address = player.getAddress().toString();
		cache.add("INSERT INTO `logger`(`uuid`, `displayName`, `action`, `data`, `address`) VALUES ("+UUID+","+displayName+","+action+","+ data +","+address+")");
		return false;
	}
	
	public boolean playerKillEntity(Player player, Entity entity) {
		String UUID = player.getUniqueId().toString();
		String displayName = player.getDisplayName();
		String action = "playerKillEntity";
		String data = entity.getName().toString();
		String address = player.getAddress().toString();
		cache.add("INSERT INTO `logger`(`uuid`, `displayName`, `action`, `data`, `address`) VALUES ("+UUID+","+displayName+","+action+","+data+","+address+")");
		return false;
	}
	
	public boolean moneyTransaction(Player player, Map<String,Object> map) {
		return false;
	}
	
	public boolean playerJoin(Player player) {
		String UUID = player.getUniqueId().toString();
		String displayName = player.getDisplayName();
		String action = "playerJoin";
		String data = "";
		String address = player.getAddress().toString();
		cache.add("INSERT INTO `logger`(`uuid`, `displayName`, `action`, `data`, `address`) VALUES ("+UUID+","+displayName+","+action+","+data+","+address+")");
		return false;
	}
	
	public boolean playerQuit(Player player) {
		String UUID = player.getUniqueId().toString();
		String displayName = player.getDisplayName();
		String action = "playerQuit";
		String data = "";
		String address = player.getAddress().toString();
		cache.add("INSERT INTO `logger`(`uuid`, `displayName`, `action`, `data`, `address`) VALUES ("+UUID+","+displayName+","+action+","+data+","+address+")");
		return false;
	}
	
	public boolean rankChange(Player player, HashMap<String,Object> map) {
		return false;
	}
	
	public boolean playerFly(Player player) {
		String UUID = player.getUniqueId().toString();
		String displayName = player.getDisplayName();
		String action = "playerFly";
		String data = "";
		String address = player.getAddress().toString();
		cache.add("INSERT INTO `logger`(`uuid`, `displayName`, `action`, `data`, `address`) VALUES ("+UUID+","+displayName+","+action+","+data+","+address+")");
		return false;
	}
}
