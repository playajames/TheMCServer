package me.playajames.tmcs.handler;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;

public class UtilitiesHandler {

	public void serializeLoc(Location loc) {
		
	}
	
	public Location deSerializeLoc() {
		return null;
	}
	
	public void kickAllPlayers() {
		for (Player player : GlobalData.onlinePlayers) {
			player.kickPlayer(GlobalData.styleChatServer + "Don't worry, the server has been stopped. As a result all players have been kicked. Check our website for updates.");
		}
	}
}
