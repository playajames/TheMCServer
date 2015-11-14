package me.playajames.tmcs;

import org.bukkit.plugin.java.JavaPlugin;

import me.playajames.tmcs.commands.TMCS;
import me.playajames.tmcs.listeners.PlayerJoin;

public class Main extends JavaPlugin {
	
	public void onDisable() {
		this.getLogger().info("Plugin has been disabled.");
	}
	
	public void onEnable(){
		this.getLogger().info("Plugin has been enabled.");
		
		// Register Events
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

		// Register Commands
		this.getCommand("tmcs").setExecutor(new TMCS());
	}  
}
