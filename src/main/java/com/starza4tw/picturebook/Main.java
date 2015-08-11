package com.starza4tw.picturebook;

import java.io.IOException;
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
		plugin = this;
		getCommand("picturebook").setExecutor(new CommandHandler(this));
		getServer().getPluginManager().registerEvents(new ChatHandler(), this);	
		saveDefaultConfig();
		ConfigHandler.RegisterFilter();
		initializeMetrics();
		logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.GREEN + " Picturebook " + getDescription().getVersion() + " has been enabled!");
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
	
	public void initializeMetrics()
	{
		try 
		{
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	    }
		catch (IOException exception) 
		{
			logger.severe(ChatColor.GOLD + "[Picturebook] " + ChatColor.RED + exception);
	    }
	}
}