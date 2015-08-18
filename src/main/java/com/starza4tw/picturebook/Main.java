package com.starza4tw.picturebook;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	public static Logger logger = Logger.getLogger("Minecraft");
	private static Main plugin;
	
	@Override
	public void onEnable()
	{
		logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.GREEN + " Picturebook " + getDescription().getVersion() + " has been enabled!");
		plugin = this;
		getCommand("picturebook").setExecutor(new CommandHandler(this));
		getServer().getPluginManager().registerEvents(new ChatHandler(), this);	
		saveDefaultConfig();
		ConfigHandler.RegisterFilter();
		ConfigHandler.initializeMetrics();
		if(UpdateHandler.getLatestVersion() == Main.getInstance().getDescription().getVersion())
		{
			logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.BLUE +" Using Latest Version! Using Version: " + Main.getInstance().getDescription().getVersion());
		}
		else if(UpdateHandler.getLatestVersion() != Main.getInstance().getDescription().getVersion())
		{
			logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.BLUE +" Either you are using a dev. build, or an update was found! Found Version: " + UpdateHandler.getLatestVersion());
		}
	}
	
	@Override
	public void onDisable()
	{
		logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Picturebook " + getDescription().getVersion() + " has been disabled!");
	}
	
	public static Main getInstance()
	{
		return plugin;
	}
}