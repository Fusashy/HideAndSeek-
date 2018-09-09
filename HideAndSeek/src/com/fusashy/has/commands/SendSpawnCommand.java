package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class SendSpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length <= 0) {
			sender.sendMessage(ChatColor.RED + "Veuillez ajouter des arguments à cette commande.");
			return false;
		}
		
		if(args[0].equalsIgnoreCase("all")) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.teleport(p.getWorld().getSpawnLocation());
				p.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " vous a téléporté au spawn.");
			}
			sender.sendMessage(ChatColor.GOLD + "Tout les joueurs ont été téléportés au spawn.");
			return true;
		}
			Player bkt = Bukkit.getPlayer(args[0]);
			
			if(bkt != null) {
				bkt.teleport(bkt.getWorld().getSpawnLocation());
				bkt.sendMessage(ChatColor.RED + sender.getName() + ChatColor.GOLD + " vous a téléporté au spawn.");
				sender.sendMessage(ChatColor.GOLD + "Vous avez téléporté " + ChatColor.RED + bkt.getName() + ChatColor.GOLD +  " au spawn.");
				return false;
			}else {
				sender.sendMessage(ChatColor.RED + "Ce joueur est introuvable !");
				return false;
			}
			
		
	}
}
