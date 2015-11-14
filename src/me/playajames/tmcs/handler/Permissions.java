package me.playajames.tmcs.handler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Permissions {

	public void deny(Player player, String cmd) {
		player.sendMessage(ChatColor.RED + "Sorry, you don't have enough permissions.");
		Bukkit.getLogger().warning("[PermissionsEx] User " + player.getDisplayName() + " tried to access chat command '" + cmd + "', but dosen't have permission to do this.");;
	}
}
