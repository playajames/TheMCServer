package me.playajames.tmcs.handler;

import java.util.Date;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.playajames.tmcs.GlobalData;
import me.playajames.tmcs.Main;
import me.playajames.tmcs.persistence.ClanMembersTable;
import me.playajames.tmcs.persistence.ClansTable;
import me.playajames.tmcs.persistence.PlayersTable;

public class ClansHandler {

	private int value = 10;
	
	public void create(String name, String prefix, boolean inviteOnly, Player player) {
		if (new ClansTable().get(name) == null) {
			if (new ClanMembersTable().get(player.getUniqueId().toString()) == null) {
				int money = (int) new PlayersTable().get(player.getUniqueId().toString(), "money");
				if (money >= value) {
					money = money - value;
					new PlayersTable().set(player.getUniqueId().toString(), "money", String.valueOf(money));
					ClansTable clanClass = new ClansTable();
					clanClass.setName(name);
					clanClass.setPrefix(prefix);
					clanClass.setMembers(0);
					clanClass.setOwnerUuid(player.getUniqueId().toString());
					clanClass.setInviteOnly(inviteOnly);
					clanClass.setTimestamp(new Date().toString());
					Main.getPlugin().getDatabase().save(clanClass);
					addMember(player, name, "ipkhd");
					player.sendMessage(GlobalData.styleChatServer + "Your new clan has been created!");
				} else {
					player.sendMessage(GlobalData.styleChatServer + "You dont have enough money to create a clan.");
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "Looks like you are already in a clan.");
			}
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Sorry, there is already a clan with that name.");
		}
	}
	
	public void delete(Player player) {
		ClanMembersTable clanMemberClass = new ClanMembersTable().get(player.getUniqueId().toString());
		if (clanMemberClass != null) {
			if (clanMemberClass.getPermissions() != null && clanMemberClass.getPermissions().contains("d")) {
				ClansTable clanClass = new ClansTable().get(clanMemberClass.getClanId());
				if (clanClass != null) {
					List<? extends ClanMembersTable> membersList = new ClanMembersTable().getAllMembers(String.valueOf(clanMemberClass.getClanId()));
					for (ClanMembersTable memberClass : membersList) {
						Main.getPlugin().getDatabase().delete(memberClass);
					}
					Main.getPlugin().getDatabase().delete(clanClass);
					player.sendMessage(GlobalData.styleChatServer + "You have successfully deleted your clan.");
				} else {
					player.sendMessage(GlobalData.styleChatServer + "That clan doesn't exist.");
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "You dont have enough permissions to do this.");
			}
		} else {
			player.sendMessage(GlobalData.styleChatServer + "You are not part of a clan with permission to delete it.");
		}
	}
	
	public void invite(Player player, String targetPlayerName) {
		
	}
	
	public void list(Player player) {
		List<? extends ClansTable> clansList = new ClansTable().get();
		if (clansList != null) {
			player.sendMessage(ChatColor.YELLOW + "Clan's List");
			int i = 1;
			for (ClansTable clanClass : clansList) {
				player.sendMessage(ChatColor.YELLOW + "" + i + ". " + ChatColor.GRAY + clanClass.getName());
				i++;
			}
		} else {
			player.sendMessage(GlobalData.styleChatServer + "There are no clans.");
		}
	}
	
	public void lookup(Player player, String name) {
		//
	}
	
	public void join(Player player, String name) {
		ClanMembersTable clanMemberClass = new ClanMembersTable().get(player.getUniqueId().toString());
		if (clanMemberClass == null) {
			ClansTable clanClass = new ClansTable().get(name);
			if (clanClass != null) {
				if (!clanClass.isInviteOnly()) {
					addMember(player, name);
				} else {
					player.sendMessage(GlobalData.styleChatServer + "This clan requires that you be invited to join.");
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "Couldn't find that clan.");
			}
		} else {
			player.sendMessage(GlobalData.styleChatServer + "You are already in a clan.");
		}
	}
	
	public void leave(Player player) {
		
	}
	
	public void kick(Player player, String targetPlayerName) {
		
	}
	
	public void setPermission(Player player, String targetPlayerName, String permissions) {
		
	}
	
	public void updatePrefix(Player player, String prefix) {
		
	}
	
	public void setHome(Player player) {
		
	}
	
	public void home(Player player) {
		
	}
	
	public void showHelp(Player player) {
		player.sendMessage("This is the help command.");
	}
	
	public void addMember(Player player, String name) {
		if (new ClanMembersTable().get(player.getUniqueId().toString()) == null) {
			ClansTable clanClass = new ClansTable().get(name);
			if (clanClass != null) {
				ClanMembersTable clanMemberClass = new ClanMembersTable();
				clanMemberClass.setClanId(clanClass.getId());
				clanMemberClass.setUuid(player.getUniqueId().toString());
				clanMemberClass.setTimestamp(new Date().toString());
				clanClass.setMembers(clanClass.getMembers()+1);
				Main.getPlugin().getDatabase().save(clanMemberClass);
				Main.getPlugin().getDatabase().save(clanClass);
				player.sendMessage(GlobalData.styleChatServer + "You are now a member of " + name + ".");
			} else {
				player.sendMessage(GlobalData.styleChatServer + "That clan doesn't exist.");
			}
		} else {
			player.sendMessage(GlobalData.styleChatServer + "You are already in a clan.");
		}
	}
	
	public void addMember(Player player, String name, String permissions) {
		if (new ClanMembersTable().get(player.getUniqueId().toString()) == null) {
			ClansTable clanClass = new ClansTable().get(name);
			if (clanClass != null) {
				ClanMembersTable clanMemberClass = new ClanMembersTable();
				clanMemberClass.setClanId(clanClass.getId());
				clanMemberClass.setPermissions(permissions);
				clanMemberClass.setUuid(player.getUniqueId().toString());
				clanMemberClass.setTimestamp(new Date().toString());
				clanClass.setMembers(clanClass.getMembers()+1);
				Main.getPlugin().getDatabase().save(clanMemberClass);
				Main.getPlugin().getDatabase().save(clanClass);
			} else {
				player.sendMessage(GlobalData.styleChatServer + "That clan doesn't exist.");
			}
		} else {
			player.sendMessage(GlobalData.styleChatServer + "You are already in a clan.");
		}
	}
	
	public boolean pvpCheck(Player player, Player targetPlayer) {
		ClanMembersTable clanMemberClass = new ClanMembersTable().get(player.getUniqueId().toString());
		if (clanMemberClass != null) {
			ClanMembersTable targetClanMemberClass = new ClanMembersTable().get(targetPlayer.getUniqueId().toString());
			if (targetClanMemberClass != null) {
				if (clanMemberClass.getClanId() == targetClanMemberClass.getClanId()) {
					return true;
				}
			}
		}
		return false;
	}
}
