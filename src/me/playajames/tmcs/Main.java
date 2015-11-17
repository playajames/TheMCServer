package me.playajames.tmcs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.bukkit.plugin.java.JavaPlugin;

import me.playajames.tmcs.commands.Database;
import me.playajames.tmcs.commands.TMCS;
import me.playajames.tmcs.listeners.EntityDeath;
import me.playajames.tmcs.listeners.PlayerBreakBlock;
import me.playajames.tmcs.listeners.PlayerDeath;
import me.playajames.tmcs.listeners.PlayerJoin;
import me.playajames.tmcs.listeners.PlayerPlaceBlock;
import me.playajames.tmcs.listeners.PlayerQuit;
import me.playajames.tmcs.persistence.Players;

public class Main extends JavaPlugin {
	
	public void onDisable() {
		this.getLogger().info("Plugin has been disabled.");
	}
	
	public void onEnable(){
		this.getLogger().info("Plugin has been enabled.");
		setupDatabase();
		
		// Register Events
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		getServer().getPluginManager().registerEvents(new PlayerBreakBlock(), this);
		getServer().getPluginManager().registerEvents(new PlayerPlaceBlock(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		getServer().getPluginManager().registerEvents(new EntityDeath(), this);

		// Register Commands
		this.getCommand("tmcs").setExecutor(new TMCS());
		this.getCommand("database").setExecutor(new Database(this));
		this.getCommand("db").setExecutor(new Database(this));
	}
	
	private void setupDatabase() {
		try {
			getDatabase().find(Players.class).findRowCount();
		} catch(PersistenceException ex) {
			System.out.println("Installing database for " + getDescription().getName() + " due to first time usage.");
			installDDL();
		}
	}
	
	@Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Players.class);
        return list;
    }
}
