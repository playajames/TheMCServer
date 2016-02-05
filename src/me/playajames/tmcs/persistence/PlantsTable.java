package me.playajames.tmcs.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;

import me.playajames.tmcs.Main;

@Entity()
@Table(name = "Plants")
public class PlantsTable {

	@Id
    private int id;
    @Length (max = 40)
    @NotNull
    private String world;
    @NotNull
    private int locX;
    @NotNull
    private int locY;
    @NotNull
    private int locZ;
    @NotNull
    private int type;
    @NotNull
    private int stage;
    private int light;
    private int health;
    private double growRate;
    private int waterLevel;
    private int nutrients;
    @Length (max = 40)
    @NotNull
    private String plantedTime;
    @Length (max = 40)
    private String lastCheckTime;
	
    public void setId(int value) {
        this.id = value;
    }
 
    public int getId() {
        return id;
    }
    
    public void setWorld(String value) {
        this.world = value;
    }
 
    public String getWorld() {
        return world;
    }
    
    public void setLocX(int value) {
        this.locX = value;
    }
 
    public int getLocX() {
        return locX;
    }
    
    public void setLocY(int value) {
        this.locY = value;
    }
 
    public int getLocY() {
        return locY;
    }
    
    public void setLocZ(int value) {
        this.locZ = value;
    }
 
    public int getLocZ() {
        return locZ;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int value) {
        this.type = value;
    }
    
    public int getStage() {
        return stage;
    }
    
    public void setStage(int value) {
        this.stage = value;
    }
    
    public int getLight() {
        return light;
    }
    
    public void setLight(int value) {
        this.light = value;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int value) {
        this.health = value;
    }
    
    public double getGrowRate() {
        return growRate;
    }
    
    public void setGrowRate(double value) {
        this.growRate = value;
    }
    
    public int getWaterLevel() {
        return waterLevel;
    }
    
    public void setWaterLevel(int value) {
        this.waterLevel = value;
    }
    
    public int getNutrients() {
        return nutrients;
    }
    
    public void setNutrients(int value) {
        this.nutrients = value;
    }
    
    public String getPlantedTime() {
        return plantedTime;
    }
    
    public void setPlantedTime(String value) {
        this.plantedTime = value;
    }
    
    public String getLastCheckTime() {
        return lastCheckTime;
    }
    
    public void setLastCheckTime(String value) {
        this.lastCheckTime = value;
    }
    public boolean set(String uuid, String key, String value) {
    	PlantsTable plantsClass = Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().find(this.getClass()).where().ieq("id", String.valueOf(id)).findUnique();
    	if (key == null) {
			return false;
		} else {
			try {
				switch (key.toLowerCase()) {
				case "id":
					plantsClass.setId(Integer.parseInt(value));
					break;
				case "locx":
					plantsClass.setLocX(Integer.parseInt(value));
					break;
				case "locy":
					plantsClass.setLocY(Integer.parseInt(value));
					break;
				case "locz":
					plantsClass.setLocZ(Integer.parseInt(value));
					break;
				case "type":
					plantsClass.setType(Integer.parseInt(value));
					break;
				case "stage":
					plantsClass.setStage(Integer.parseInt(value));
					break;
				case "health":
					plantsClass.setHealth(Integer.parseInt(value));
					break;
				case "growrate":
					plantsClass.setGrowRate(Integer.parseInt(value));
					break;
				case "waterlevel":
					plantsClass.setWaterLevel(Integer.parseInt(value));
					break;
				case "nutrients":
					plantsClass.setNutrients(Integer.parseInt(value));
					break;
				case "plantedtime":
					plantsClass.setPlantedTime(value);
					break;
				case "lastchecktime":
					plantsClass.setLastCheckTime(value);
					break;
				default:
					return false;
				}
				Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(plantsClass);
			} catch(Exception e) {
				System.out.println(e);
				return false;
			}
		}
    	return true;
    }
    
	public Object get(Location loc, String key) {
		PlantsTable plantsClass;
		String x = String.valueOf(loc.getBlockX());
		String y = String.valueOf(loc.getBlockY());
		String z = String.valueOf(loc.getBlockZ());
		plantsClass = Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().find(this.getClass()).where().ieq("locX", x).ieq("locY", y).ieq("locZ", z).findUnique();
		if (plantsClass == null) {
			return null;
		} else if (key == null) {
			return plantsClass;
		} else {
			try {
				switch (key.toLowerCase()) {
				case "id":
					return (int) plantsClass.getId();
				case "locx":
					return (double) plantsClass.getLocX();
				case "locy":
					return (double) plantsClass.getLocY();
				case "locz":
					return (double) plantsClass.getLocZ();
				case "type":
					return (int) plantsClass.getType();
				case "stage":
					return (int) plantsClass.getStage();
				case "health":
					return (int) plantsClass.getHealth();
				case "growrate":
					return (int) plantsClass.getGrowRate();
				case "waterlevel":
					return (int) plantsClass.getWaterLevel();
				case "nutrients":
					return (int) plantsClass.getNutrients();
				case "plantedtime":
					return (String) plantsClass.getPlantedTime();
				case "lastchecktime":
					return (String) plantsClass.getLastCheckTime();
				default:
					return false;
				}
			} catch(Exception e) {
				System.out.println(e);
				return false;
			}
		}
	}
	
	public boolean contains(Location loc) {
		PlantsTable plantsClass;
		String x = String.valueOf(loc.getBlockX());
		String y = String.valueOf(loc.getBlockY());
		String z = String.valueOf(loc.getBlockZ());
		plantsClass = Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().find(this.getClass()).where().ieq("locX", x).ieq("locY", y).ieq("locZ", z).findUnique();
		if (plantsClass != null) return true;
		return false;
	}
	
	public List<? extends PlantsTable> get() {
    	List<? extends PlantsTable> plantList = Main.getPlugin().getDatabase().find(this.getClass()).findList();
    	return plantList;
	}
}
