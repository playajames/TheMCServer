package me.playajames.tmcs.handler;

import java.util.List;

import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.commands.WarpCommand;
import me.playajames.tmcs.persistence.JailTable;

public class JailHandler {

	
	public boolean checkJailed(Player player) {
		if (player.hasMetadata("jailed")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void jailPlayer(Player player, double duration, Player jailedBy) {
		JailTable jailClass = getAvaliableJail();
		new WarpCommand().warp(player, jailClass.getWarpName());
		
		///Much more to do here!
		player.sendMessage(GlobalData.styleChatServer + "You have been jailed for " + duration + " seconds, by" + jailedBy.getDisplayName() + ".");

	}
	
	private JailTable getAvaliableJail() {
		List<? extends JailTable> jailList = new JailTable().get();
		for (JailTable jailClass : jailList) {
			if (jailClass.getOccupied() == false) {
				return jailClass;
			}
		}
		return null;
	}
}
