package me.playajames.tmcs.persistence;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.avaje.ebean.validation.NotNull;

import me.playajames.tmcs.Main;

@Entity()
@Table(name = "jails")
public class JailTable {

	@Id
	private int id;
	@NotNull
	private String warpName;
	@NotNull
	private Boolean occupied;
	private String occupiedByUUID;
	@NotNull
	private double duration;
	@NotNull
	private String timestamp;
	@Version
    @Column(columnDefinition = "timestamp default '2014-10-06 21:17:06'")
    public Timestamp version;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setWarpName(String value) {
		this.warpName = value;
	}
	
	public String getWarpName() {
		return this.warpName;
	}
	
	public void setOccupied(Boolean value) {
		this.occupied = value;
	}
	
	public Boolean getOccupied() {
		return this.occupied;
	}
	
	public void setOccupiedByUUID(String value) {
		this.occupiedByUUID = value;
	}
	
	public String getOccupiedByUUID() {
		return this.occupiedByUUID;
	}
	
	public void setDuration(double value) {
		this.duration = value;
	}
	
	public double getDuration() {
		return this.duration;
	}
	
	public void setTimestamp(String value) {
		this.timestamp = value;
	}
	
	public String getTimestamp() {
		return this.timestamp;
	}
	
	public void setVersion(Timestamp value) {
		this.version = value;
	}
	
	public Timestamp getVersion() {
		return this.version;
	}
	
	public JailTable get(String name) {
    	JailTable jailClass = Main.getPlugin().getDatabase().find(this.getClass()).where().ieq("warpName", name).findUnique();
    	return jailClass;
	}
	
	public List<? extends JailTable> get() {
    	List<? extends JailTable> jailList = Main.getPlugin().getDatabase().find(this.getClass()).findList();
    	return jailList;
	}
}
