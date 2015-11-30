package me.playajames.tmcs.persistence;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;

import me.playajames.tmcs.Main;

@Entity()
@Table(name = "warps")
public class WarpsTable {

	@Id
	private int id;
	@Length(max = 40)
	@NotNull
	private String name;
	@NotNull
	private String world;
	@NotNull
	private double locX;
	@NotNull
	private double locY;
	@NotNull
	private double locZ;
	@NotNull
	private float pitch;
	@NotNull
	private float yaw;
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
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setWorld(String value) {
		this.world = value;
	}
	
	public String getWorld() {
		return this.world;
	}
	
	public void setLocX(double value) {
		this.locX = value;
	}
	
	public double getLocX() {
		return this.locX;
	}
	
	public void setLocY(double value) {
		this.locY = value;
	}
	
	public double getLocY() {
		return this.locY;
	}
	
	public void setLocZ(double value) {
		this.locZ = value;
	}
	
	public double getLocZ() {
		return this.locZ;
	}
	
	public void setPitch(float value) {
		this.pitch = value;
	}
	
	public float getPitch() {
		return this.pitch;
	}
	
	public void setYaw(float value) {
		this.yaw = value;
	}
	
	public float getYaw() {
		return this.yaw;
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
	
	public WarpsTable get(String name) {
    	WarpsTable warpClass = Main.getPlugin().getDatabase().find(this.getClass()).where().ieq("name", name).findUnique();
    	return warpClass;
	}
	
	public List<? extends WarpsTable> get() {
    	List<? extends WarpsTable> warpsList = Main.getPlugin().getDatabase().find(this.getClass()).findList();
    	return warpsList;
	}
	
}
