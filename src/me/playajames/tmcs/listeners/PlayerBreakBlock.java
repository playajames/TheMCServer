package me.playajames.tmcs.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.blocks.WeedStress;
import me.playajames.tmcs.handler.LoggerHandler;

public class PlayerBreakBlock implements Listener{

	///// On Break
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		if (event.getPlayer() instanceof Player) {
			
			Player player = event.getPlayer();
			new LoggerHandler().blockBreak(player, event.getBlock());
			
			if (event.getBlock().getType().equals(Material.CROPS)) new WeedStress().harvestCheck(event);
			if (new WeedStress().checkPlantedOn(event.getBlock())) {
				event.setCancelled(true);
				player.sendMessage(GlobalData.styleChatServer + "You can't break a block with a custom item on it.");
			}
			
		}
	}
}
