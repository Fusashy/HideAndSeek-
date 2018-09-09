package com.fusashy.has.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class HealCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
		
			Player p = (Player) sender;
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 255));
			p.sendMessage(ChatColor.GOLD + "Vous avez été régénéré.");
			
		}
		return false;
	}
}
