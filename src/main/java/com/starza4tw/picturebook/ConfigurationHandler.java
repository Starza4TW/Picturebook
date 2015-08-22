package com.starza4tw.picturebook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.mcstats.Metrics;

public class ConfigurationHandler 
{
	public static HashMap<String, String> FilterList = new HashMap<String, String>();
	public static Object[] keyArray;
	public static Object[] valueArray;

	public static void RegisterFilter()
	{
		Set<String> keys = Main.getInstance().getConfig().getConfigurationSection("Filter").getKeys(false);
		Map<String, Object> values = Main.getInstance().getConfig().getConfigurationSection("Filter").getValues(false);
		if(keys.size() == values.size())
		{
			for(Object keyToAdd : keys)
			{
				FilterList.put(keyToAdd.toString(), values.get(keyToAdd).toString());
			}
			 keyArray = FilterList.keySet().toArray();
			 valueArray = FilterList.values().toArray();
		}
	}
	
	public static void initializeMetrics()
	{
		try 
		{
	        Metrics metrics = new Metrics((Main.getInstance()));
	        metrics.start();
	    }
		catch (IOException exception) 
		{
			Main.logger.severe(ChatColor.GOLD + "[Picturebook] " + ChatColor.RED + exception);
	    }
	}
}