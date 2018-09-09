package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class KickCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
		if(args.length <= 0) {
			sender.sendMessage(ChatColor.RED + "Veuillez ajouter des arguments à cette commande.");
			return false;
		}
	
		
		if(args[0].equalsIgnoreCase("all")) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(!p.equals(sender)) {
					p.kickPlayer(ChatColor.GOLD + "Vous avez été explulsé du serveur par " + ChatColor.RED +sender.getName());
				}
			}
			sender.sendMessage(ChatColor.GOLD + "Tout les joueurs ont été expulsés du serveur.");
			return true;
		}
			Player bkt = Bukkit.getPlayer(args[0]);
			
			if(bkt != null) {
				bkt.kickPlayer(ChatColor.GOLD + "Vous avez été explulsé du serveur par " + ChatColor.RED +sender.getName());
				sender.sendMessage(ChatColor.GOLD + "Vous avez expulsé " + ChatColor.RED + bkt.getName() + ChatColor.GOLD +  " du serveur.");
				return false;
			}else {
				sender.sendMessage(ChatColor.RED + "Ce joueur est introuvable !");
				return false;
			}

	} else {
		sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
		return false;
	}
		
	}
}