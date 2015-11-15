package me.playajames.tmcs;

import org.bukkit.ChatColor;

public class GlobalData {

	///// Default Style for all MCL Responses
	public static String styleChatServer = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "TMCS" + ChatColor.DARK_GRAY + "]" + ChatColor.GOLD + " ";
	
	
	///// MySQL Connection Info
	public static String HOST      = null;
	public static String PORT      = null;
	public static String USER      = null;
	public static String PASS      = null;
	public static String DATABASE  = null;
	
	///// Misc Variables
	public static Boolean pluginInitialized = false;

}
