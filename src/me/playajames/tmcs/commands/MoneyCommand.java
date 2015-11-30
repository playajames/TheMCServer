package me.playajames.tmcs.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.handler.PermissionsHandler;
import me.playajames.tmcs.persistence.PlayersTable;

public class MoneyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				if (player.hasPermission("tmcs.command.money")) {
					int money = getMoney(player);
					player.sendMessage(ChatColor.GREEN + "Money: " + ChatColor.GRAY + money);
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} 
			else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					showHelp(player);
					return false;
				}
				
				if (player.hasPermission("tmcs.command.money.lookup")) {
					OfflinePlayer targetPlayer = getPlayer(args[0]);
					if (targetPlayer != null) {
						int money = getMoney(targetPlayer);
						if (money != -1) {
							player.sendMessage(ChatColor.GREEN + targetPlayer.getName() + "'s Money: " + ChatColor.GRAY + money);
						} else {
							player.sendMessage(GlobalData.styleChatServer + "Couldn't find specified player.");
						}
					} else {
						player.sendMessage(GlobalData.styleChatServer + "Couldn't find specified player.");
					}
				} else {
					new PermissionsHandler().denyCommand(player, cmd, args);
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "Invalid argument. Use /money help for more info.");

			}
		}
		return false;
	}

	private int getMoney(OfflinePlayer player) {
		try {
			int money = (int) new PlayersTable().get(player.getUniqueId().toString(), "money");
			System.out.println(money);
			return money;
		} catch(NullPointerException exception) {
			return -1;
		}
	}
	
	private int getMoney(Player player) {
		return (int) new PlayersTable().get(player.getUniqueId().toString(), "money");
	}
	
	@SuppressWarnings("deprecation")
	private OfflinePlayer getPlayer(String name) {
		return Bukkit.getOfflinePlayer(name);
	}
	
	private void showHelp(Player player) {
		player.sendMessage(ChatColor.YELLOW + "***Money Command Help***");
		player.sendMessage(ChatColor.YELLOW + "Alais: money");
		player.sendMessage(ChatColor.YELLOW + "Usage:");
		player.sendMessage(ChatColor.YELLOW + " - /money - Returns your players money.");
		player.sendMessage(ChatColor.YELLOW + " - /money <playername> - Returns specified players money.");
	}
}
