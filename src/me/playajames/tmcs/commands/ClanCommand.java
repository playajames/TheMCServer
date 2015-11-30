package me.playajames.tmcs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.ClansHandler;
import me.playajames.tmcs.handler.PermissionsHandler;

public class ClanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length == 0) {
				player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Use /clan help for more info.");
				return false;
			}
			
			else if (args[0].equals("create") && args.length == 4) {
				if (player.hasPermission("tmcs.command.clan.create")) {
					if (args[3].equalsIgnoreCase("0") || args[3].equalsIgnoreCase("1")) {
						new ClansHandler().create(args[1], args[2], Boolean.getBoolean(args[3]), player);
					} else {
						player.sendMessage(GlobalData.styleChatServer + "Invalid argument: " + args[3] + " Argument must be 0 or 1.");
					}
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove") && args.length == 1) {
				if (player.hasPermission("tmcs.command.clan.delete")) {
					new ClansHandler().delete(player);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("invite") && args.length == 2) {
				if (player.hasPermission("tmcs.command.clan.invite")) {
					new ClansHandler().invite(player, args[1]);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("list") && args.length == 1) {
				if (player.hasPermission("tmcs.command.clan.list")) {
					new ClansHandler().list(player);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("lookup") && args.length == 2) {
				if (player.hasPermission("tmcs.command.clan.lookup")) {
					new ClansHandler().lookup(player, args[1]);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("join") && args.length == 2) {
				if (player.hasPermission("tmcs.command.clan.join")) {
					new ClansHandler().join(player, args[1]);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("leave") && args.length == 1) {
				if (player.hasPermission("tmcs.command.clan.leave")) {
					new ClansHandler().leave(player);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("kick") && args.length == 2) {
				if (player.hasPermission("tmcs.command.clan.kick")) {
					new ClansHandler().kick(player, args[1]);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("setpermission") || args[0].equalsIgnoreCase("setpermissions") && args.length == 3) {
				if (player.hasPermission("tmcs.command.clan.setpermission")) {
					new ClansHandler().setPermission(player, args[1], args[2]);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("prefix") && args.length == 2) {
				if (player.hasPermission("tmcs.command.clan.prefix")) {
					new ClansHandler().updatePrefix(player, args[1]);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("sethome") && args.length == 2) {
				if (player.hasPermission("tmcs.command.clan.sethome")) {
					new ClansHandler().setHome(player);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("home") && args.length == 1) {
				if (player.hasPermission("tmcs.command.clan.home")) {
					new ClansHandler().home(player);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else if (args[0].equalsIgnoreCase("help") && args.length == 2) {
				if (player.hasPermission("tmcs.command.clan.help")) {
					new ClansHandler().showHelp(player);;
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			
			else {
				player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Use /clan help for more info.");
			}
		}
		return false;
	}

}
