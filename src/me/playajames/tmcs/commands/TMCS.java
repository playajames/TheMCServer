package me.playajames.tmcs.commands;

import org.bukkit.Bukkit;
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

public class TMCS implements CommandExecutor {

	private Main plugin;
	public TMCS (Main plugin) {
        this.plugin = plugin;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("debug")) {
					if (player.hasPermission("tmcs.debug")) {
						player.sendMessage(GlobalData.styleChatServer + "Theres nothing here.");
					} else {
						new Permissions().deny(player, cmd.toString() + " " + args.toString());
					}
				} else if (args[0].equalsIgnoreCase("db")) {
					databaseLookupMethod(player, args);
				} else {
					player.sendMessage(GlobalData.styleChatServer + "I'm not sure what to do, use /tmcs help for more info.");
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "I'm not sure what to do, use /tmcs help for more info.");
			}
		} else if (sender instanceof ConsoleCommandSender) {
			if (args.length > 0) {
				if (args[0].equals("debug")) {
					Bukkit.getLogger().info("Theres nothing here.");
				} else {
					Bukkit.getLogger().info("I'm not sure what to do, use /tmcs help for more info.");
				}
			} else {
				Bukkit.getLogger().info("I'm not sure what to do, use /tmcs help for more info.");
			}
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private boolean databaseLookupMethod(Player player, String[] args) {
		//tmcs db set playajames key value
		//0    1   2      3       4    5
		if (args.length == 5 && args[1].equalsIgnoreCase("set")) {
			String uuid = plugin.getServer().getPlayer(args[3]).getUniqueId().toString();
			String key = args[4];
			String value = args[5];
			if (new Players().set(uuid, key, value)) {
				player.sendMessage("Database entry has been set!");
			} else {
				player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. Check the arguments and try again. Usage: /tmcs db set playername key value");
			}
		} else if (args.length == 4 && args[1].equalsIgnoreCase("get")) {
			String uuid = plugin.getServer().getPlayer(args[3]).getUniqueId().toString();
			String key = args[4];
			Object returned = new Players().get(uuid, key);
			if (!returned.equals(false)) {
				if (returned instanceof Integer) {
					returned = String.valueOf(returned);
				}
				player.sendMessage(ChatColor.GREEN + "Playername: " + new Players().get(uuid, "playerName"));
				player.sendMessage(ChatColor.GREEN + key + ": " + returned); 
			} else {
				player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Oops, something went wrong. Check the arguments and try again. Usage: /tmcs db get playername key");
			}
		} else {
			player.sendMessage("Invalid.");
			return false;
		}
		return false;
	}
}
