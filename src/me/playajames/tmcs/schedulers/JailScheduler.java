package me.playajames.tmcs.schedulers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import me.playajames.tmcs.Main;
import me.playajames.tmcs.persistence.JailTable;

public class JailScheduler {
	public void init() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
        	
        	@Override
            public void run() {
        		@SuppressWarnings("unchecked")
				List<JailTable> list = (List<JailTable>) new JailTable().getOccupiedList();
                for (JailTable jailClass : list) {
                	
                }
            }
        }, 0L, 100L);
	}
}
