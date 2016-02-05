package me.playajames.tmcs.commands;

import java.util.Date;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.Main;
import me.playajames.tmcs.handler.PermissionsHandler;
import me.playajames.tmcs.persistence.WarpsTable;

public class WarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 0) {
				player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Use /warp help for more info.");
				return false;
			} else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete") && args.length == 2) {
				if (player.hasPermission("tmcs.command.warp.remove")) {
					removeWarp(player, args[1]);
					return true;
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("create") && args.length == 2) {
				if (player.hasPermission("tmcs.command.warp.create")) {
					createWarp(player, args[1]);
					return true;
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("update") && args.length == 2) {
				if (player.hasPermission("tmcs.command.warp.update")) {
					updateWarp(player, args[1]);
					return true;
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("list") && args.length == 1) {
				if (player.hasPermission("tmcs.command.warp.list")) {
					listWarps(player);
					return true;
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args.length == 1 && !args[0].equalsIgnoreCase("help")) {
				if (player.hasPermission("tmcs.command.warp." + args[0])) {
					warp(player, args[0]);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("help")) {
				if (player.hasPermission("tmcs.command.warp.help")) {
					showHelp(player);
					return true;
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Use /warp help for more info.");
			}
		}
		return false;
	}
	
	public void showHelp(Player player) {
		player.sendMessage(ChatColor.YELLOW + "***Warp Command Help***");
		player.sendMessage(ChatColor.YELLOW + "Alais: warp, warps");
		player.sendMessage(ChatColor.YELLOW + "Usage:");
		player.sendMessage(ChatColor.YELLOW + " - /warp create <warp_name> - Creates new warp with specified name at current player location.");
		player.sendMessage(ChatColor.YELLOW + " - /warp update <warp_name> - Updates location of existing specified warp to players current location.");
		player.sendMessage(ChatColor.YELLOW + " - /warp remove <warp_name> - Removes specified warp forever.");

	}
	
	public void listWarps(Player player) {
		List<? extends WarpsTable> warpsList = new WarpsTable().get();
		int i = 1;
		player.sendMessage(ChatColor.YELLOW + "***Warp's List***");
		for (WarpsTable warpClass : warpsList) {
			player.sendMessage(ChatColor.YELLOW + "" + i + ". " + warpClass.getName());
			i++;
		}
	}
	
	public void createWarp(Player player, String name) {
		Date now = new Date();
		WarpsTable warpClass = new WarpsTable().get(name);
		if (warpClass == null) {
			Location loc = player.getLocation();
			warpClass = new WarpsTable();
			warpClass.setName(name);
			warpClass.setWorld(loc.getWorld().getName());
			warpClass.setLocX(loc.getX());
			warpClass.setLocY(loc.getY());
			warpClass.setLocZ(loc.getZ());
			warpClass.setPitch(loc.getPitch());
			warpClass.setYaw(loc.getYaw());
			warpClass.setListed(true);
			warpClass.setTimestamp(now.toString());
			Main.getPlugin().getDatabase().save(warpClass);
			player.sendMessage(GlobalData.styleChatServer + "Created a new warp named " + name + ".");
		} else {
			player.sendMessage(GlobalData.styleChatServer + "There is already a warp named " + name + " choose another name or delete the conflicting warp.");
		}
	}
	
	public void removeWarp(Player player, String name) {
		//Warps warpClass = new Warps().get(name);
		WarpsTable warpClass = Main.getPlugin().getDatabase().find(WarpsTable.class).where().ieq("name", name).findUnique();
		if (warpClass != null) {
			try {
				Main.getPlugin().getDatabase().delete(warpClass);
			} catch (Exception exception){
				player.sendMessage(GlobalData.styleChatServer + "Something went wrong.");
				System.out.println("There was a problem deleting warp [name=" + name + "].");
				exception.printStackTrace();
				return;
			}
			player.sendMessage(GlobalData.styleChatServer + "Removed warp named " + name + ".");
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Couldn't find any warp's named " + name + ".");
		}
	}
	
	public void updateWarp(Player player, String name) {
		WarpsTable warpClass = new WarpsTable().get(name);
		if (warpClass != null) {
			Location loc = player.getLocation();
			warpClass.setWorld(loc.getWorld().getName());
			warpClass.setLocX(loc.getX());
			warpClass.setLocY(loc.getY());
			warpClass.setLocZ(loc.getZ());
			warpClass.setPitch(loc.getPitch());
			warpClass.setYaw(loc.getYaw());
			Main.getPlugin().getDatabase().update(warpClass);
			player.sendMessage(GlobalData.styleChatServer + "Updated warp location named " + name + ".");
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Couldn't find any warp's named " + name + ".");
		}
	}

	public void warp(Player player, String name) {
		WarpsTable warpClass = new WarpsTable().get(name);
		if (warpClass != null) {
			World world = Main.getPlugin().getServer().getWorld(warpClass.getWorld());
			double x = warpClass.getLocX();
			double y = warpClass.getLocY();
			double z = warpClass.getLocZ();
			float pitch = warpClass.getPitch();
			float yaw = warpClass.getYaw();
			Location loc = new Location(world, x, y, z, yaw, pitch);
			player.teleport(loc);
			player.sendMessage(GlobalData.styleChatServer + "You have been teleported to " + name + ".");
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Couldn't find any warp's named " + name + ".");
		}
	}
	
	public void warp(Player player, String name, long cooldown) {
		//here
	}
}
