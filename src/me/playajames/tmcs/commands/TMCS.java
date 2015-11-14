package me.playajames.tmcs.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.Permissions;

public class TMCS implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equals("debug")) {
					if (player.hasPermission("tmcs.debug")) {
						player.sendMessage(GlobalData.styleChatServer + "Players in array: " + String.valueOf(GlobalData.players.size()));
					} else {
						new Permissions().deny(player, "tmcs debug");
					}
				} else {
					player.sendMessage(GlobalData.styleChatServer + "I'm not sure what to do, use /tmcs help for more info.");
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "I'm not sure what to do, use /tmcs help for more info.");
			}
		} else if (sender instanceof ConsoleCommandSender) {
			if (args.length > 0) {
				if (args[0].equals("debug")) {
					Bukkit.getLogger().info("Players in array: " + String.valueOf(GlobalData.players.size()));
				} else {
					Bukkit.getLogger().info("I'm not sure what to do, use /tmcs help for more info.");
				}
			} else {
				Bukkit.getLogger().info("I'm not sure what to do, use /tmcs help for more info.");
			}
		}
		return false;
	}
}
