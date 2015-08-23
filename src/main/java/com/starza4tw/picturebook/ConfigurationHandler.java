package com.starza4tw.picturebook;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;

public class ConfigurationHandler 
{
	public static int currentConfigurationRevision = 2;
	public static HashMap<String, String> FilterList = new HashMap<String, String>();
	public static Object[] keyArray;
	public static Object[] valueArray;

	public static void registerConfiguration()
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
		
	public static void checkForConfigurationUpdate()
	{
		if(Main.getInstance().getConfig().getInt("configRevision", 0) != currentConfigurationRevision)
		{
			Main.logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.BLUE + "Found an outdated config.yml, now updating to latest revision!");
			new File(Main.getInstance().getDataFolder(), "config.yml").renameTo(new File(Main.getInstance().getDataFolder(), "old-config.yml"));
			Main.getInstance().saveDefaultConfig();
			Main.getInstance().reloadConfig();
			ConfigurationHandler.FilterList.clear();
			ConfigurationHandler.registerConfiguration();
			Main.logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.WHITE + " Updated Configuration File.");
		}
	}
}