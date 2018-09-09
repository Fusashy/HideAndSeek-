package com.fusashy.has.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			sender.sendMessage(ChatColor.GOLD + "Le point de spawn à été défini.");
			((Player) sender).getWorld().setSpawnLocation(((Player) sender).getLocation());
			
		}
		return false;
	}
}
