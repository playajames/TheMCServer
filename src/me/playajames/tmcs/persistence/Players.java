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
    private int timePlayed;
    private String data;
    
    public void setId(int id) {
        this.id = id;
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
    
    public int getTimePlayed() {
    	return timePlayed;
    }
    
    public void setTimePlayed(int value) {
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
    
    public boolean set(String uuid, String key, Object value) {
    	Players playerClass = Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().find(Players.class).where().ieq("uuid", uuid).findUnique();
    	if (playerClass == null) {
			return false;
		} else {
			try {
				switch (key.toLowerCase()) {
				case "displayname":
					setDisplayName((String)value);
					break;
				case "groupid":
					setGroupID((int)value);
					break;
				case "address":
					setAddress((String)value);
					break;
				case "infractions":
					setInfractions((int)value);
					break;
				case "money":
					setMoney((int)value);
					break;
				case "license":
					setLicense((String)value);
					break;
				case "kills":
					setKills((int)value);
					break;
				case "deaths":
					setDeaths((int)value);
					break;
				case "monsterkills":
					setMonsterKills((int)value);
					break;
				case "firstjointimestamp":
					setFirstJoinTimestamp((String)value);
					break;
				case "lastjointimestamp":
					setLastJoinTimestamp((String)value);
					break;
				case "lastquittimestamp":
					setLastQuitTimestamp((String)value);
					break;
				case "timeplayed":
					setTimePlayed((int)value);
					break;
				case "data":
					setData((String)value);
					break;
				default:
					return false;
				}
			} catch(Exception e) {
				return false;
			}
		}
    	return true;
    }
    
	public Object get(String uuid, String key) {
    	Players playerClass = Bukkit.getPluginManager().getPlugin("TMCS").getDatabase().find(Players.class).where().ieq("uuid", uuid).findUnique();
    	if (playerClass == null) {
			return null;
		} else if (key == null) {
			return playerClass;
		} else {
			try {
				switch (key.toLowerCase()) {
				case "id":
					return (int) getId();
				case "displayname":
					return (String) getDisplayName();
				case "groupid":
					return (int) getGroupID();
				case "address":
					return (String) getAddress();
				case "infractions":
					return (int) getInfractions();
				case "money":
					return (int)getMoney();
				case "license":
					return (String) getLicense();
				case "kills":
					return (int) getKills();
				case "deaths":
					return (int) getDeaths();
				case "monsterkills":
					return (int) getMonsterKills();
				case "firstjointimestamp":
					return (String) getFirstJoinTimestamp();
				case "lastjointimestamp":
					return (String) getLastJoinTimestamp();
				case "lastquittimestamp":
					return (String) getLastQuitTimestamp();
				case "timeplayed":
					return (int) getTimePlayed();
				case "data":
					return (String) getData();
				default:
					return false;
				}
			} catch(Exception e) {
				return false;
			}
		}
	}
}
