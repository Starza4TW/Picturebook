package com.starza4tw.picturebook;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;

public class FilterHandler implements Listener
{	
	public static ArrayList<Inventory> openAnvilInventories = new ArrayList<Inventory>();
	public static ItemRenameTask renameTask = new ItemRenameTask();
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent chatEvent)
	{
		if(chatEvent.getPlayer().hasPermission("picturebook.chatfilter") == true)
		{
			for(String Key : ConfigurationHandler.FilterList.keySet())
			{
				if(chatEvent.getMessage().toLowerCase().contains(Key.toLowerCase()))
				{
					chatEvent.setMessage(chatEvent.getMessage().replaceAll("(?i)" + Key, ConfigurationHandler.FilterList.get(Key)));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent commandEvent)
	{
		if(commandEvent.getPlayer().hasPermission("picturebook.commandfilter") == true)
		{
			for(String Key : ConfigurationHandler.FilterList.keySet())
			{
				if(commandEvent.getMessage().toLowerCase().contains(Key.toLowerCase()))
				{
					commandEvent.setMessage(commandEvent.getMessage().replaceAll("(?i)" + Key, ConfigurationHandler.FilterList.get(Key)));
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent inventoryOpenEvent)
	{
		if(inventoryOpenEvent.getInventory().getType() == InventoryType.ANVIL)
		{
			if(inventoryOpenEvent.getPlayer().hasPermission("picturebook.namefilter") == true)
			{
				openAnvilInventories.add(inventoryOpenEvent.getInventory());
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent inventoryCloseEvent)
	{
		if(inventoryCloseEvent.getInventory().getType() == InventoryType.ANVIL)
		{
			if(inventoryCloseEvent.getPlayer().hasPermission("picturebook.namefilter") == true)
			{
				if(openAnvilInventories.contains(inventoryCloseEvent.getInventory()))
				{
					openAnvilInventories.remove(inventoryCloseEvent.getInventory());
				}
			}
		}
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent signChangeEvent)
	{
		if(signChangeEvent.getPlayer().hasPermission("picturebook.signfilter") == true)
		{
			for(int line = 0; line <= 3; line++)
			{
				for(String Key : ConfigurationHandler.FilterList.keySet())
				{
					if(signChangeEvent.getLine(line).toLowerCase().contains(Key.toLowerCase()))
					{
						signChangeEvent.setLine(line, signChangeEvent.getLine(line).replaceAll("(?i)" + Key, ConfigurationHandler.FilterList.get(Key)));
					}
				}
			}
		}
	}
}