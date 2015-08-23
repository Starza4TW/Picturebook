package com.starza4tw.picturebook;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor
{
	public Integer pageNumber;
	public Integer endCount;
	public String formatLabel;
	private final Main plugin;
	
	public CommandHandler(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if (command.getName().equalsIgnoreCase("picturebook")) 
		{
			if(args.length == 0 || args[0].equalsIgnoreCase("help"))
			{
				if(sender.hasPermission("picturebook.command") == true)
				{
					sender.sendMessage(new String[] {ChatColor.GOLD + "[Picturebook (pb) - Help]", ChatColor.BLUE + "/picturebook add [Filter] [Replacement] - " + ChatColor.WHITE + "Adds a filter for Picturebook to search for.", ChatColor.BLUE + "/picturebook list (Page) - " + ChatColor.WHITE + "Lists filtered words and their replacements.", ChatColor.BLUE + "/picturebook reload - " + ChatColor.WHITE + "Reloads Picturebook's config.", ChatColor.BLUE + "/picturebook remove [Filter] " + ChatColor.WHITE + "Removes a Filter from Picturebook's Filter List.", ChatColor.BLUE + "/picturebook update - " + ChatColor.WHITE + "Check for updates of Picturebook.", ChatColor.BLUE + "/picturebook version - " + ChatColor.WHITE + "Lists basic information about Picturebook."});
					return true;
				}
				else if(sender.hasPermission("picturebook.command") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("add"))
			{
				if(sender.hasPermission("picturebook.add") == true)
				{
					if((args != null) && (args.length == 3))
					{
						plugin.getConfig().set("Filter."+ args[1], args[2]);
						plugin.saveConfig();
						plugin.reloadConfig();
						ConfigurationHandler.FilterList.clear();
						ConfigurationHandler.registerConfiguration();
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.WHITE + " Added Replacement to Filter List!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please list two arguments after the command!");
						return true;
					}
				}
				else if(sender.hasPermission("picturebook.add") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("list"))
			{
				if(sender.hasPermission("picturebook.list") == true)
				{
					if(args.length >= 3)
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" Please list the correct number of arguments!");
						return true;
					}
					else if(args.length == 1)
					{
						pageNumber = 1;
					}
					else if(args.length == 2)
					{
						try
						{
							pageNumber = Integer.parseInt(args[1]);
						}
						catch(NumberFormatException exception)
						{
							sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please input an Integer greater than 0 for the page number!");
							return true;
						}
					}
					
					if((pageNumber <= (Math.ceil((double)ConfigurationHandler.FilterList.size() / 10))) && (pageNumber != 0))
					{
						if(ConfigurationHandler.valueArray.length > ((10 * pageNumber) - 1) && ConfigurationHandler.valueArray[(10 * pageNumber) - 1] != null)
						{
							endCount = (10 * pageNumber) - 1;
						}
						else
						{
							endCount = ConfigurationHandler.valueArray.length - 1;
						}
						sender.sendMessage(ChatColor.GOLD + "[Picturebook] - Filter List");
						for(int Count = (10 * (pageNumber - 1)); Count <= endCount; Count++)
						{
							if(ChatColor.stripColor(ConfigurationHandler.valueArray[Count].toString()).length() == 0)
							{
								formatLabel = "FORMAT TEST";
							}
							else if(ChatColor.stripColor(ConfigurationHandler.valueArray[Count].toString()).length() > 0)
							{
								formatLabel = "";
							}
							sender.sendMessage(ConfigurationHandler.keyArray[Count].toString() + ": " + ConfigurationHandler.valueArray[Count].toString() + formatLabel);
						}
						sender.sendMessage(ChatColor.GOLD + "[Picturebook] " + ChatColor.WHITE + "Showing page " + ChatColor.GREEN + pageNumber + ChatColor.WHITE + "/" + ChatColor.RED + ((int)Math.ceil((double)ConfigurationHandler.FilterList.size() / 10)) + ChatColor.AQUA + " [" + ConfigurationHandler.FilterList.size() + " Total Replacements]");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please input an Integer greater than 0 for the page number!");
						return true;
					}
				}
				else if(sender.hasPermission("picturebook.list") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("reload"))
			{
				if(sender.hasPermission("picturebook.reload") == true)
				{
					plugin.reloadConfig();
					ConfigurationHandler.FilterList.clear();
					ConfigurationHandler.registerConfiguration();
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.WHITE + " Reloaded Configuration File.");
					return true;
				}
				else if (sender.hasPermission("picturebook.reload") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("remove"))
			{
				if(sender.hasPermission("picturebook.remove") == true)
				{
					if((args != null) && (args.length == 2))
					{
						plugin.getConfig().set("Filter." + args[1], null);
						plugin.saveConfig();
						plugin.reloadConfig();
						ConfigurationHandler.FilterList.clear();
						ConfigurationHandler.registerConfiguration();
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.WHITE + " Removed Replacement from Filter List!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please list an argument after the command!");
						return true;
					}
				}
				else if (sender.hasPermission("picturebook.remove") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("update"))
			{
				if(sender.hasPermission("picturebook.update") == true)
				{
					if(UpdateHandler.getLatestVersion() == Main.getInstance().getDescription().getVersion())
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.BLUE +" Using Latest Version! Using Version: " + Main.getInstance().getDescription().getVersion());
						return true;
					}
					else if(UpdateHandler.getLatestVersion() != Main.getInstance().getDescription().getVersion())
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.BLUE +" Either you are using a dev build, or an update was found! Found Version: " + UpdateHandler.getLatestVersion());
						return true;
					}
				}
				else if(sender.hasPermission("picturebook.update") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("version"))
			{
				if(sender.hasPermission("picturebook.version") == true)
				{
					sender.sendMessage(new String[] {ChatColor.GOLD + "[Picturebook - Version]", ChatColor.BLUE + "Version: " + ChatColor.WHITE + plugin.getDescription().getVersion(), ChatColor.BLUE + "Author(s): " + ChatColor.WHITE + plugin.getDescription().getAuthors(), ChatColor.BLUE + "Website: " + ChatColor.WHITE + plugin.getDescription().getWebsite()});
					return true;
				}
				else if(sender.hasPermission("picturebook.version") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
		}
		return false; 
	}
}