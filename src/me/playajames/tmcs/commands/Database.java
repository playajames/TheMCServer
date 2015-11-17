package me.playajames.tmcs.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.Main;
import me.playajames.tmcs.handler.Permissions;
import me.playajames.tmcs.persistence.Players;

public class Database implements CommandExecutor {

	/*
	 * 	Command Usage:
	 * 		/database set playername key value - Sets value of specified key.
	 * 		/database get playername key - Returns value of specified key.
	 */
	
	private Main plugin;
	public Database (Main plugin) {
        this.plugin = plugin;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				switch (args[0].toLowerCase()) {
				case "help":
					if (player.hasPermission("tmcs.command.database.help")) {
						showHelp(player);
					} else {
						new Permissions().denyCommand(player, cmd, args);
					}
					break;
				case "set":
					if (player.hasPermission("tmcs.command.database.set")) {
						if (args.length == 4) {
							if (!databaseSet(player, args)) {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. For more info use /db help.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Check arguments. Use /database help for more info.");
						}
					} else {
						new Permissions().denyCommand(player, cmd, args);
					}
					break;
				case "get":
					if (player.hasPermission("tmcs.command.database.get")) {
						if (args.length == 3) {
							if (!databaseGet(player, args)) {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. For more info use /db help.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Check arguments. Use /database help for more info.");
						}
					} else {
						new Permissions().denyCommand(player, cmd, args);
					}
					break;
				default:
					player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Check arguments. Use /database help for more info.");
					break;
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Check arguments. Use /database help for more info.");
				return true;
			}
		} else if (sender instanceof ConsoleCommandSender) {
			
		}
		return true;
	}
	
	private void showHelp(Player player) {
		player.sendMessage(ChatColor.GOLD + "***Database Command Help***");
		player.sendMessage(ChatColor.GOLD + "Alais: database, db");
		player.sendMessage(ChatColor.GOLD + "Usage:");
		player.sendMessage(ChatColor.GOLD + " - /database set playername key value - Sets value of specified key.");
		player.sendMessage(ChatColor.GOLD + " - /database get playername key - Returns value of specified key.");
	}
	
	@SuppressWarnings("deprecation")
	private boolean databaseSet(Player player, String[] args) {
		Player targetPlayer = plugin.getServer().getPlayer(args[1]);
		String key = args[2];
		String value = args[3];
		if (new Players().set(targetPlayer.getUniqueId().toString(), key, value)) {
			player.sendMessage("Database entry " + key + " has been set to " + value + " for " + targetPlayer.getDisplayName());
			return true;
		}else {
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	private boolean databaseGet(Player player, String[] args) {
		Player targetPlayer = plugin.getServer().getPlayer(args[1]);
		String key = args[2];
		Object returned = new Players().get(targetPlayer.getUniqueId().toString(), key);
		if (!returned.equals(false)) {
			if (returned instanceof Integer) {
				returned = String.valueOf(returned);
			}
			player.sendMessage(ChatColor.GREEN + "Playername: " + targetPlayer.getDisplayName());
			player.sendMessage(ChatColor.GREEN + key + ": " + returned); 
		} else {
			player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. For more info use /db help.");
		}
		return true;
	}
}