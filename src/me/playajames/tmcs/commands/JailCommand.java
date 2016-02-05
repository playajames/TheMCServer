package me.playajames.tmcs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.JailHandler;
import me.playajames.tmcs.handler.PermissionsHandler;

public class JailCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args[0].equalsIgnoreCase("create")) {
				if (player.hasPermission("tds.command.jail.create")) {
					if (args.length == 2) { createJail(player, args[1]); } else { player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Usage: /jail create <warp_to_jail>"); }
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
				if (player.hasPermission("tds.command.jail.remove")) {
					if (args.length == 2) { removeJail(player, args[1]); } else { player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Usage: /jail remove <warp_to_jail>"); }
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("sentance")) {
				if (player.hasPermission("tds.command.jail.sentance")) {
					if (args.length == 3) { sentanceToJail(); } else { player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Usage: /jail sentance <playername> <duration_seconds>"); }
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("release")) {
				if (player.hasPermission("tds.command.jail.release")) {
					if (args.length == 2) { releaseFromJail(); } else { player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Usage: /jail release <playername>"); }
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else if (args[0].equalsIgnoreCase("help")) {
				showHelp();
			} else {
				showHelp();
			}
		}
		return false;
	}

	private void releaseFromJail() {
		// TODO Auto-generated method stub
		
	}

	private void sentanceToJail() {
		// TODO Auto-generated method stub
		
	}

	private void removeJail(Player player, String warpName) {
		new JailHandler().removeJail(player, warpName);
	}

	private void createJail(Player player, String warpName) {
		new JailHandler().createJail(player, warpName);
	}

	private void showHelp() {
		// TODO Auto-generated method stub
		
	}

}
