package me.playajames.tmcs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GlobalData {

	///// Default Style for all MCL Responses
	public static String styleChatServer = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "TMCS" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";
	
	
	///// MySQL Connection Info
	public static String HOST      = null;
	public static String PORT      = null;
	public static String USER      = null;
	public static String PASS      = null;
	public static String DATABASE  = null;
	
	///// Misc Variables
	public static List<Player> onlinePlayers = new ArrayList<Player>();
	public static Boolean pluginInitialized = false;

	// Debug
	public static boolean debug = true;
	
}
