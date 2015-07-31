package com.starza4tw.picturebook;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener
{
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		if(event.getPlayer().hasPermission("picturebook.filter") == true)
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