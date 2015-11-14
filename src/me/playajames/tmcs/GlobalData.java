package me.playajames.tmcs;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.permissions.PermissionAttachment;

public class GlobalData {

	///// Default Style for all MCL Responses
	public static String styleChatServer = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "TMCS" + ChatColor.DARK_GRAY + "]" + ChatColor.GOLD + " ";
	public static String denyPermission = ChatColor.RED + "Sorry, you don't have enough permissions.";
	
	
	///// MySQL Connection Info
	public static String HOST      = null;
	public static String PORT      = null;
	public static String USER      = null;
	public static String PASS      = null;
	public static String DATABASE  = null;
	
	///// Misc Variables
	public static Boolean pluginInitialized = false;
	public static HashMap<UUID, HashMap<String,Object>> players = new HashMap<UUID, HashMap<String,Object>>();
	public static HashMap<UUID, PermissionAttachment> permissions = new HashMap<UUID, PermissionAttachment>();
	public static HashMap<String, Boolean> serverProperties = new HashMap<String, Boolean>();
//	public static ArrayList<Chunk> chunkForceLoaded = new ArrayList<Chunk>();

}
