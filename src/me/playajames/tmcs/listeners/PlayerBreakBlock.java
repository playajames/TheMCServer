package me.playajames.tmcs.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.playajames.tmcs.blocks.WeedStress;
import me.playajames.tmcs.handler.Logger;

public class PlayerBreakBlock implements Listener{

	///// On Break
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		new Logger().blockBreak(event.getPlayer(), event.getBlock());
		if (event.getBlock().getType().equals(Material.CROPS)) new WeedStress().harvestCheck(event);
	}
}
