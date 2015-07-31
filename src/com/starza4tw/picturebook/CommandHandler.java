package com.starza4tw.picturebook;

import net.md_5.bungee.api.ChatColor;

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
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("picturebook")) 
		{
			if(args.length == 0 || args[0].equalsIgnoreCase("help"))
			{
				if(sender.hasPermission("picturebook.filter") == true)
				{
					sender.sendMessage(new String[] {ChatColor.GOLD + "[Picturebook (pb) - Help]", ChatColor.BLUE + "/picturebook add [Filter] [Replacement] - " + ChatColor.WHITE + "Adds a filter for Picturebook to search for.", ChatColor.BLUE + "/picturebook list [Page] - " + ChatColor.WHITE + "Lists filtered words and their replacements.", ChatColor.BLUE + "/picturebook reload - " + ChatColor.WHITE + "Reloads Picturebook's config.", ChatColor.BLUE + "/picturebook remove [Filter] " + ChatColor.WHITE + "Removes a Filter from Picturebook's Filter List.", ChatColor.BLUE + "/picturebook version - " + ChatColor.WHITE + "Lists basic information about Picturebook."});
					return true;
				}
				else if(sender.hasPermission("picturebook.filter") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("add"))
			{
				if(sender.hasPermission("picturebook.admin") == true)
				{
					if((args != null) && (args.length == 3))
					{
						plugin.getConfig().set("Filter."+ args[1], args[2]);
						plugin.saveConfig();
						plugin.reloadConfig();
						ConfigHandler.FilterList.clear();
						ConfigHandler.RegisterFilter();
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.WHITE + " Added Replacement to Filter List!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please list two arguments after the command!");
						return true;
					}
				}
				else if(sender.hasPermission("picturebook.admin") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("list"))
			{
				if(sender.hasPermission("picturebook.filter") == true)
				{
					if((args != null) && (args.length == 2))
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
						
						if((pageNumber <= (Math.ceil((double)ConfigHandler.FilterList.size() / 10))) && (pageNumber != 0))
						{
							if(ConfigHandler.valueArray.length > ((10 * pageNumber) - 1) && ConfigHandler.valueArray[(10 * pageNumber) - 1] != null)
							{
								endCount = (10 * pageNumber) - 1;
							}
							else
							{
								endCount = ConfigHandler.valueArray.length - 1;
							}
							sender.sendMessage(ChatColor.GOLD + "[Picturebook] - Filter List");
							for(int Count = (10 * (pageNumber - 1)); Count <= endCount; Count++)
							{
								if(ChatColor.stripColor(ConfigHandler.valueArray[Count].toString()).length() == 0)
								{
									formatLabel = "FORMAT TEST";
								}
								else if(ChatColor.stripColor(ConfigHandler.valueArray[Count].toString()).length() > 0)
								{
									formatLabel = "";
								}
								sender.sendMessage(ConfigHandler.keyArray[Count].toString() + ": " + ConfigHandler.valueArray[Count].toString() + formatLabel);
							}
							sender.sendMessage(ChatColor.GOLD + "[Picturebook] " + ChatColor.WHITE + "Showing page " + ChatColor.GREEN + pageNumber + ChatColor.WHITE + "/" + ChatColor.RED + ((int)Math.ceil((double)ConfigHandler.FilterList.size() / 10)) + ChatColor.AQUA + " [" + ConfigHandler.FilterList.size() + " Total Replacements]");
							return true;
						}
						else
						{
							sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please input an Integer greater than 0 for the page number!");
							return true;
						}
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please input an Integer greater than 0 for the page number!");
						return true;
					}
				}
				else if(sender.hasPermission("picturebook.filter") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("reload"))
			{
				if(sender.hasPermission("picturebook.admin") == true)
				{
					plugin.reloadConfig();
					ConfigHandler.FilterList.clear();
					ConfigHandler.RegisterFilter();
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.WHITE + " Reloaded Configuration File.");
					return true;
				}
				else if (sender.hasPermission("picturebook.admin") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("remove"))
			{
				if(sender.hasPermission("picturebook.filter") == true)
				{
					if((args != null) && (args.length == 2))
					{
						plugin.getConfig().set("Filter." + args[1], null);
						plugin.saveConfig();
						plugin.reloadConfig();
						ConfigHandler.FilterList.clear();
						ConfigHandler.RegisterFilter();
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.WHITE + " Removed Replacement from Filter List!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED + " Please list two arguments after the command!");
						return true;
					}
				}
				else if (sender.hasPermission("picturebook.filter") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
			else if(args[0].equalsIgnoreCase("version"))
			{
				if(sender.hasPermission("picturebook.filter") == true)
				{
					sender.sendMessage(new String[] {ChatColor.GOLD + "[Picturebook - Version]", ChatColor.BLUE + "Version: " + ChatColor.WHITE + plugin.getDescription().getVersion(), ChatColor.BLUE + "Author(s): " + ChatColor.WHITE + plugin.getDescription().getAuthors(), ChatColor.BLUE + "Website: " + ChatColor.WHITE + plugin.getDescription().getWebsite()});
					return true;
				}
				else if(sender.hasPermission("picturebook.filter") == false)
				{
					sender.sendMessage(ChatColor.GOLD + "[Picturebook]" + ChatColor.RED +" You don't have the permission to do that!");
					return true;
				}
			}
		}
		return false; 
	}
}