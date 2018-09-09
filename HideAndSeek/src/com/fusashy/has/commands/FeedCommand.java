package com.fusashy.has.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class FeedCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
		Player p = (Player) sender;
		  p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2, 255));
		p.sendMessage(ChatColor.GOLD + "Vous n'avez plus faim ! ");
	
		}
		
		return false;
	}
}
