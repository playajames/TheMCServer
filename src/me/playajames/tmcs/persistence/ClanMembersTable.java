package me.playajames.tmcs.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

import me.playajames.tmcs.Main;

@Entity()
@Table(name = "clan_members")
public class ClanMembersTable {
	@Id
	private int id;
	@NotNull
	private String uuid;
	@NotNull
	private int clanId;
	private String permissions;
	@NotNull
	private String timestamp;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setUuid(String value) {
		this.uuid = value;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	public void setClanId(int value) {
		this.clanId = value;
	}
	
	public int getClanId() {
		return this.clanId;
	}
	
	public void setPermissions(String value) {
		this.permissions = value;
	}
	
	public String getPermissions() {
		return this.permissions;
	}
	
	public void setTimestamp(String value) {
		this.timestamp = value;
	}
	
	public String getTimestamp() {
		return this.timestamp;
	}
	
	//////
	
	public ClanMembersTable get(String uuid) {
		ClanMembersTable clanMemberClass = Main.getPlugin().getDatabase().find(this.getClass()).where().ieq("uuid", uuid).findUnique();
		if (clanMemberClass != null) {
			return clanMemberClass;
		} else {
			return null;
		}
	}
	
	public List<? extends ClanMembersTable> getAllMembers(String clanId) {
		List<? extends ClanMembersTable> memberList = (List<? extends ClanMembersTable>) Main.getPlugin().getDatabase().find(this.getClass()).where().ieq("clanId", clanId).findList();
		if (memberList != null) {
			return memberList;
		} else {
			return null;
		}
	}
}
