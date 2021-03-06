package me.playajames.tmcs.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.PermissionsHandler;
import me.playajames.tmcs.persistence.PlayersTable;

public class DatabaseCommand implements CommandExecutor {

	/*
	 * 	Command Usage:
	 * 		/database set playername key value - Sets value of specified key.
	 * 		/database get playername key - Returns value of specified key.
	 */
		
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
						new PermissionsHandler().denyCommand(player, cmd, args);
					}
					break;
				case "set":
					if (player.hasPermission("tmcs.command.database.set")) {
						if (args.length == 4) {
							if (!databaseSetPlayer(player, args)) {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. For more info use /db help.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Check arguments. Use /database help for more info.");
						}
					} else {
						new PermissionsHandler().denyCommand(player, cmd, args);
					}
					break;
				case "get":
					if (player.hasPermission("tmcs.command.database.get")) {
						if (args.length == 3) {
							if (!databaseGetPlayer(player, args)) {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. For more info use /db help.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Check arguments. Use /database help for more info.");
						}
					} else {
						new PermissionsHandler().denyCommand(player, cmd, args);
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
			System.out.println("This command is only avaliable to players at this time.");
		} else {
			return false;
		}
		return true;
	}
	
	private void showHelp(Player player) {
		player.sendMessage(ChatColor.YELLOW + "***Database Command Help***");
		player.sendMessage(ChatColor.YELLOW + "Alais: database, db");
		player.sendMessage(ChatColor.YELLOW + "Usage:");
		player.sendMessage(ChatColor.YELLOW + " - /database set <playername> <key> <value> - Sets value of specified key.");
		player.sendMessage(ChatColor.YELLOW + " - /database get <playername> <key> - Returns value of specified key.");
	}
	
	@SuppressWarnings("deprecation")
	private boolean databaseSetPlayer(Player player, String[] args) {
		OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
		String key = args[2];
		String value = args[3];
		if (new PlayersTable().set(targetPlayer.getUniqueId().toString(), key, value)) {
			player.sendMessage(GlobalData.styleChatServer + "Database entry " + key + " has been set to " + value + " for " + targetPlayer.getName());
			return true;
		}else {
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	private boolean databaseGetPlayer(Player player, String[] args) {
		OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
		String key = args[2];
		Object returned = new PlayersTable().get(targetPlayer.getUniqueId().toString(), null);
		if (!returned.equals(false)) {
			if (returned instanceof Integer) {
				returned = String.valueOf(returned);
			}
			player.sendMessage(ChatColor.GREEN + "Playername: " + targetPlayer.getName());
			player.sendMessage(ChatColor.GREEN + key + ": " + returned); 
		} else {
			player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. For more info use /db help.");
		}
		return true;
	}
}