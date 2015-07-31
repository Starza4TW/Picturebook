package com.starza4tw.picturebook;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConfigHandler 
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
}