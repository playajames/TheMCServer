package me.playajames.tmcs.handler;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Permissions {

	public void denyCommand(Player player, Command cmd, String[] args) {
		player.sendMessage(ChatColor.RED + "Sorry, you don't have enough permissions.");
		Bukkit.getLogger().warning("[PermissionsEx] User " + player.getDisplayName() + " tried to access chat command '" + cmd.getName() + Arrays.toString(args) + "', but dosen't have permission to do this.");
		return;
	}
}
