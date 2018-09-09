package com.fusashy.has.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class NickCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length <= 0) {
				p.sendMessage(ChatColor.RED + "C'est mieux avec un pseudo.");
				return false;
			}
			
			String nick = args[0];
			if(nick.length() > 16) {
				p.sendMessage(ChatColor.RED+ "Choisissez un pseudo plus court.");
				return false;
			}else {
				p.setDisplayName(nick);
				p.setCustomName(nick);
				p.setCustomNameVisible(true);
				p.setPlayerListName(nick);
				p.sendMessage( ChatColor.GREEN + "Votre nom est désormais : " + nick);
			}
			
		
		}else {
			sender.sendMessage("Nope.");
		}
		

	} else {
		sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
	}
		return false;
	}
}
