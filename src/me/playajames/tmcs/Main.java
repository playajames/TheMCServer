package me.playajames.tmcs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.bukkit.plugin.java.JavaPlugin;

import me.playajames.tmcs.commands.DatabaseCommand;
import me.playajames.tmcs.commands.MoneyCommand;
import me.playajames.tmcs.commands.TMCSCommand;
import me.playajames.tmcs.commands.TestCommand;
import me.playajames.tmcs.commands.WarpCommand;
import me.playajames.tmcs.items.CustomItems;
import me.playajames.tmcs.listeners.BlockGrow;
import me.playajames.tmcs.listeners.EntityDeath;
import me.playajames.tmcs.listeners.InventoryClick;
import me.playajames.tmcs.listeners.PlayerBreakBlock;
import me.playajames.tmcs.listeners.PlayerDeath;
import me.playajames.tmcs.listeners.PlayerInteract;
import me.playajames.tmcs.listeners.PlayerInteractEntity;
import me.playajames.tmcs.listeners.PlayerJoin;
import me.playajames.tmcs.listeners.PlayerKick;
import me.playajames.tmcs.listeners.PlayerPlaceBlock;
import me.playajames.tmcs.listeners.PlayerQuit;
import me.playajames.tmcs.persistence.Plants;
import me.playajames.tmcs.persistence.Players;
import me.playajames.tmcs.persistence.Warps;
import me.playajames.tmcs.schedulers.PlantsScheduler;

public class Main extends JavaPlugin {
	
	public static Main getPlugin() { return JavaPlugin.getPlugin(Main.class); }
	
	public void onDisable() {
		//Needs to be fixed.
		//	new Utilities().kickAllPlayers();
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
		getServer().getPluginManager().registerEvents(new BlockGrow(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerKick(), this);
		getServer().getPluginManager().registerEvents(new InventoryClick(), this);

		// Register Commands
		this.getCommand("tmcs").setExecutor(new TMCSCommand());
		this.getCommand("database").setExecutor(new DatabaseCommand());
		this.getCommand("db").setExecutor(new DatabaseCommand());
		this.getCommand("test").setExecutor(new TestCommand());
		this.getCommand("money").setExecutor(new MoneyCommand());
		this.getCommand("warp").setExecutor(new WarpCommand());
		this.getCommand("warps").setExecutor(new WarpCommand());
		
		// Initiate Schedulers
		new PlantsScheduler(this).init();
		
		// Register Custom Items
		new CustomItems().registerItems();
	}
	
	private void setupDatabase() {
		try {
			getDatabase().find(Players.class).findRowCount();
			getDatabase().find(Plants.class).findRowCount();
		} catch(PersistenceException ex) {
			System.out.println("Installing database for " + getDescription().getName() + " due to first time usage.");
			installDDL();
		}
	}
	
	@Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Players.class);
        list.add(Plants.class);
        list.add(Warps.class);
        return list;
    }
}
