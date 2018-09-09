package com.fusashy.has.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class SpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			((Player) sender).teleport(((Player) sender).getWorld().getSpawnLocation());
			sender.sendMessage(ChatColor.GOLD + "Vous avez été téléporté au spawn.");
		}
		return false;
	}
}
