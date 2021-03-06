package com.starza4tw.picturebook;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class Main extends JavaPlugin
{
	public static Logger logger = Logger.getLogger("Minecraft");
	private static Main plugin;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		getCommand("picturebook").setExecutor(new CommandHandler(this));
		getServer().getPluginManager().registerEvents(new FilterHandler(), this);
		ConfigurationHandler.checkForConfigurationUpdate();
		ConfigurationHandler.registerConfiguration();
		if(getConfig().getBoolean("allowMetrics", true) == true)
		{
			initializeMetrics();
		}
		FilterHandler.renameTask.runTaskTimer(this, 0, getConfig().getInt("itemRenameTaskPeriod", 20));
		if(UpdateHandler.getLatestVersion() == Main.getInstance().getDescription().getVersion())
		{
			logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.BLUE +" Using Latest Version! Using Version: " + Main.getInstance().getDescription().getVersion());
		}
		else if(UpdateHandler.getLatestVersion() != Main.getInstance().getDescription().getVersion())
		{
			logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.BLUE +" Either you are using a dev. build, or an update was found! Found Version: " + UpdateHandler.getLatestVersion());
		}
		logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.GREEN + " Picturebook " + getDescription().getVersion() + " has been enabled!");
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getScheduler().cancelTasks(this);
		logger.info(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Picturebook " + getDescription().getVersion() + " has been disabled!");
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
			logger.severe(ChatColor.GOLD + "[Picturebook] " + ChatColor.RED + exception);
	    }
	}
	
	public static Main getInstance()
	{
		return plugin;
	}
}