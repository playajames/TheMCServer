package me.playajames.tmcs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.playajames.tmcs.handler.Logger;

public class PlayerPlaceBlock implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		new Logger().blockPlace(event.getPlayer(), event.getBlock());
	}
}
