package me.playajames.tmcs.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

import me.playajames.tmcs.Main;

@Entity()
@Table(name = "clans")
public class ClansTable {
	@Id
	private int id;
	@NotNull
	private String ownerUuid;
	@NotNull
	private String name;
	@NotNull
	private String prefix;
	@NotNull
	private int members;
	@NotNull
	private boolean inviteOnly;
	@NotNull
	private String timestamp;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setOwnerUuid(String value) {
		this.ownerUuid = value;
	}
	
	public String getOwnerUuid() {
		return this.ownerUuid;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPrefix(String value) {
		this.prefix = value;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public void setMembers(int value) {
		this.members = value;
	}
	
	public int getMembers() {
		return this.members;
	}
	
	public void setInviteOnly(boolean value) {
		this.inviteOnly = value;
	}
	
	public boolean getInviteOnly() {
		return this.inviteOnly;
	}
	
	public boolean isInviteOnly() {
		return this.inviteOnly;
	}
	
	public void setTimestamp(String value) {
		this.timestamp = value;
	}
	
	public String getTimestamp() {
		return this.timestamp;
	}
	
	public ClansTable get(String name) {
		ClansTable clanClass = Main.getPlugin().getDatabase().find(this.getClass()).where().ieq("name", name).findUnique();
		if (clanClass != null) {
			return clanClass;
		} else {
			return null;
		}
	}
	
	public ClansTable get(int id) {
		ClansTable clanClass = Main.getPlugin().getDatabase().find(this.getClass()).where().ieq("id", String.valueOf(id)).findUnique();
		if (clanClass != null) {
			return clanClass;
		} else {
			return null;
		}
	}
	
	public List<? extends ClansTable> get() {
		List<? extends ClansTable> clansList = Main.getPlugin().getDatabase().find(this.getClass()).findList();
		if (clansList != null) {
			return clansList;
		} else {
			return null;
		}
	}
}
