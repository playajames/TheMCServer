package me.playajames.tmcs.handler;

import java.util.Date;
import java.util.List;

import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.Main;
import me.playajames.tmcs.commands.WarpCommand;
import me.playajames.tmcs.persistence.JailTable;
import me.playajames.tmcs.persistence.WarpsTable;

public class JailHandler {
	
	public void createJail(Player player, String warpName) {
		if (new WarpsTable().get(warpName) != null) {
			JailTable jailClass = new JailTable();
			jailClass.setWarpName(warpName);
			jailClass.setOccupied(false);
			jailClass.setDuration(0);
			jailClass.setTimestamp(new Date().toString());
			Main.getPlugin().getDatabase().save(jailClass);
			player.sendMessage(GlobalData.styleChatServer + "Jail has been created.");
		} else {
			player.sendMessage(GlobalData.styleChatServer + "That warp does not exist. Make sure you have a warp created for the jail location.");
		}
	}
	
	public void removeJail(Player player, String warpName) {
		if (new JailTable().get(warpName) != null) {
			JailTable jailClass = new JailTable().get(warpName);
			Main.getPlugin().getDatabase().delete(jailClass);
			player.sendMessage(GlobalData.styleChatServer + "Jail has been deleted.");

		} else {
			player.sendMessage(GlobalData.styleChatServer + "That jail does not exist.");
		}
	}
	
	private void checkScheduler() {
		//TODO
	}
	
	private boolean checkJailed(Player player) {
		if (player.hasMetadata("jailed")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void sentancePlayer(Player player, double duration, Player jailedBy) {
		if (getAvaliableJail() == null) {
			jailedBy.sendMessage(GlobalData.styleChatServer + "Player could not be jailed. All jail cells are occupied.");
			System.out.println(player.getDisplayName() + " could not be jailed. All jail cells are occupied.");
		} else {
			JailTable jailClass = getAvaliableJail();
			new WarpCommand().warp(player, jailClass.getWarpName());
			jailClass.setOccupied(true);
			jailClass.setOccupiedByUUID(player.getUniqueId().toString());
			jailClass.setDuration(duration);
			jailClass.setTimestamp(new Date().toString());
			///Much more to do here!
			player.sendMessage(GlobalData.styleChatServer + "You have been jailed for " + duration + " seconds, by" + jailedBy.getDisplayName() + ".");	
		}
	}
	
	public void releasePlayer(Player player, Player targetPlayer) {
		
	}
	
	private JailTable getAvaliableJail() {
		List<? extends JailTable> jailList = new JailTable().getList();
		for (JailTable jailClass : jailList) {
			if (jailClass.getOccupied() == false) {
				return jailClass;
			}
		}
		return null;
	}
}
