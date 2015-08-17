package com.starza4tw.picturebook;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatHandler implements Listener
{
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		if(event.getPlayer().hasPermission("picturebook.chatfilter") == true)
		{
			for(String Key : ConfigHandler.FilterList.keySet())
			{
				if(event.getMessage().toLowerCase().contains(Key.toLowerCase()))
				{
					event.setMessage(event.getMessage().replaceAll("(?i)" + Key, ConfigHandler.FilterList.get(Key)));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event)
	{
		if(event.getPlayer().hasPermission("picturebook.commandfilter") == true)
		{
			for(String Key : ConfigHandler.FilterList.keySet())
			{
				if(event.getMessage().toLowerCase().contains(Key.toLowerCase()))
				{
					event.setMessage(event.getMessage().replaceAll("(?i)" + Key, ConfigHandler.FilterList.get(Key)));
				}
			}
		}
	}
}