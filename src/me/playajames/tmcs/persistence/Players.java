package me.playajames.tmcs.persistence;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.Main;

@Entity()
@Table(name = "players")
public class Players {
	
	
    @Id
    private int id;
    @NotNull
    private String uuid;
    @Length(max = 40)
    @NotEmpty
    private String displayName;
    private int groupID;
    @Length(max = 40)
    @NotEmpty
    private String address;
    private int infractions;
    private int money;
    private String license;
    private int kills;
    private int deaths;
    private int monsterKills;
    @Length(max = 40)
    @NotEmpty
    private String firstJoinTimestamp;
    @Length(max = 40)
    @NotEmpty
    private String lastJoinTimestamp;
    @Length(max = 40)
    private String lastQuitTimestamp;
    private long timePlayed;
    private String data;
    
    public void setId(int value) {
        this.id = value;
    }
 
    public int getId() {
        return id;
    }
    
    public String getUuid() {
    	return uuid;
    }
 
    public void setUuid(String value) {
        this.uuid = value;
    }
 
    public String getDisplayName() {
        return displayName;
    }
 
    public void setDisplayName(String value) {
        this.displayName = value;
    }
    
    public int getGroupID() {
        return groupID;
    }
    
    public void setGroupID(int value) {
        this.groupID = value;
    }
 
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String value) {
    	this.address = value;
    }
    
    public int getInfractions() {
    	return infractions;
    }
    
    public void setInfractions(int value) {
    	this.infractions = value;
    }
    
    public int getMoney() {
    	return money;
    }
    
    public void setMoney(int value) {
    	this.money = value;
    }
    
    public String getLicense() {
    	return license;
    }
    
    public void setLicense(String value) {
    	this.license = value;
    }
    
    public int getKills() {
    	return kills;
    }
    
    public void setKills(int value) {
    	this.kills = value;
    }
    
    public int getDeaths() {
    	return deaths;
    }
    
    public void setDeaths(int value) {
    	this.deaths = value;
    }
    
    public int getMonsterKills() {
    	return monsterKills;
    }
    
    public void setMonsterKills(int value) {
    	this.monsterKills = value;
    }
    
    public String getFirstJoinTimestamp() {
    	return firstJoinTimestamp;
    }
    
    public void setFirstJoinTimestamp(String value) {
    	this.firstJoinTimestamp = value;
    }
    
    public String getLastJoinTimestamp() {
    	return lastJoinTimestamp;
    }
    
    public void setLastJoinTimestamp(String value) {
    	this.lastJoinTimestamp = value;
    }
    
    public String getLastQuitTimestamp() {
    	return lastQuitTimestamp;
    }
    
    public void setLastQuitTimestamp(String value) {
    	this.lastQuitTimestamp = value;
    }
    
    public long getTimePlayed() {
    	return timePlayed;
    }
    
    public void setTimePlayed(long value) {
    	this.timePlayed = value;
    }
    
    public String getData() {
    	return data;
    }
    
    public void setData(String value) {
    	this.data = value;
    }
    
    public Player getPlayer() {
        return Bukkit.getServer().getPlayer(UUID.fromString(uuid));
    }
 
    public void setPlayer(Player player) {
        this.uuid = player.getUniqueId().toString();
    }
    
    public boolean set(String uuid, String key, String value) {
    	Players playerClass = Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().find(this.getClass()).where().ieq("uuid", uuid).findUnique();
    	if (key == null) {
			return false;
		} else {
			try {
				switch (key.toLowerCase()) {
				case "displayname":
					playerClass.setDisplayName(value);
					break;
				case "groupid":
					playerClass.setGroupID(Integer.parseInt(value));
					break;
				case "address":
					playerClass.setAddress(value);
					break;
				case "infractions":
					playerClass.setInfractions(Integer.parseInt(value));
					break;
				case "money":
					playerClass.setMoney(Integer.parseInt(value));
					break;
				case "license":
					playerClass.setLicense(value);
					break;
				case "kills":
					playerClass.setKills(Integer.parseInt(value));
					break;
				case "deaths":
					playerClass.setDeaths(Integer.parseInt(value));
					break;
				case "monsterkills":
					playerClass.setMonsterKills(Integer.parseInt(value));
					break;
				case "firstjointimestamp":
					playerClass.setFirstJoinTimestamp(value);
					break;
				case "lastjointimestamp":
					playerClass.setLastJoinTimestamp(value);
					break;
				case "lastquittimestamp":
					playerClass.setLastQuitTimestamp(value);
					break;
				case "timeplayed":
					playerClass.setTimePlayed(Integer.parseInt(value));
					break;
				case "data":
					playerClass.setData(value);
					break;
				default:
					return false;
				}
				Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().save(playerClass);
			} catch(Exception e) {
				System.out.println(e);
				return false;
			}
		}
    	return true;
    }
    
	public Object get(String uuid, String key) {
    	Players playerClass = Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().find(this.getClass()).where().ieq("uuid", uuid).findUnique();
    	if (playerClass == null) {
			return null;
		} else if (key == null) {
			return playerClass;
		} else {
			try {
				switch (key.toLowerCase()) {
				case "id":
					return (int) playerClass.getId();
				case "uuid":
					return (String) playerClass.getUuid();
				case "displayname":
					return (String) playerClass.getDisplayName();
				case "groupid":
					return (int) playerClass.getGroupID();
				case "address":
					return (String) playerClass.getAddress();
				case "infractions":
					return (int) playerClass.getInfractions();
				case "money":
					return (int) playerClass.getMoney();
				case "license":
					return (String) playerClass.getLicense();
				case "kills":
					return (int) playerClass.getKills();
				case "deaths":
					return (int) playerClass.getDeaths();
				case "monsterkills":
					return (int) playerClass.getMonsterKills();
				case "firstjointimestamp":
					return (String) playerClass.getFirstJoinTimestamp();
				case "lastjointimestamp":
					return (String) playerClass.getLastJoinTimestamp();
				case "lastquittimestamp":
					return (String) playerClass.getLastQuitTimestamp();
				case "timeplayed":
					return (long) playerClass.getTimePlayed();
				case "data":
					return (String) playerClass.getData();
				default:
					return false;
				}
			} catch(Exception e) {
				System.out.println(e);
				return false;
			}
		}
	}
	
	public void save(Players playerClass) {
		Main.getPlugin().getDatabase().save(playerClass);
	}
	
	public void kickAll() {
		for (Player player : GlobalData.onlinePlayers) {
			player.kickPlayer(GlobalData.styleChatServer + "Don't worry, the server has been stopped. As a result all players have been kicked. Check our website for updates.");
		}
	}
}
