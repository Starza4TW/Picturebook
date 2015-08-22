package com.starza4tw.picturebook;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemRenameTask extends BukkitRunnable
{	
	public ItemMeta metadata;
	
	@Override
	public void run() 
	{
		if(FilterHandler.openAnvilInventories.size() >= 1)
		{
			for(Inventory openAnvilInventory : FilterHandler.openAnvilInventories)
			{
				if(openAnvilInventory.getItem(2) != null)
				{
					if(openAnvilInventory.getItem(2).hasItemMeta() == true)
					{
						metadata = openAnvilInventory.getItem(2).getItemMeta();
						for(String Key : ConfigurationHandler.FilterList.keySet())
						{
							if(metadata.getDisplayName().toLowerCase().contains(Key.toLowerCase()))
							{
								metadata.setDisplayName(metadata.getDisplayName().replaceAll("(?i)" + Key, ConfigurationHandler.FilterList.get(Key)));
								openAnvilInventory.getItem(2).setItemMeta(metadata);
							}
						}
					}
				}
			}
		}
	}
}