package me.playajames.tmcs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.blocks.WeedStress;
import me.playajames.tmcs.handler.PermissionsHandler;

public class TestCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("tmcs.command.test")) {
				player.getInventory().addItem(new WeedStress().getSeed());
				player.sendMessage(GlobalData.styleChatServer + "Test command completed.");
			} else {
				new PermissionsHandler().denyCommand(player, cmd, args);
			}
		}
		return false;
	}
}
