package me.playajames.tmcs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.playajames.tmcs.handler.Logger;

public class PlayerBreakBlock implements Listener{

	///// On Break
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		new Logger().blockBreak(event.getPlayer(), event.getBlock());
	}
}
