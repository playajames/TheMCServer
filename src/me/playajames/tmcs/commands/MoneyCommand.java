package me.playajames.tmcs.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.persistence.Players;

public class MoneyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				int money = (int) new Players().get(player.getUniqueId().toString(), "money");
				player.sendMessage(ChatColor.GREEN + "Money: " + ChatColor.GRAY + money);
			}
		}
		return false;
	}

}
