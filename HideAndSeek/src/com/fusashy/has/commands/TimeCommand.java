package com.fusashy.has.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TimeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length <= 0) {
				sender.sendMessage(ChatColor.RED+"Veuillez ajouter un argument à cette commande.");	
				return false;
			}
			
			if(args[0].equalsIgnoreCase("day")) {
				p.setPlayerTime(1000, true);
				sender.sendMessage(ChatColor.GREEN + "Il fait jour !");	
			}else if(args[0].equalsIgnoreCase("night")) {
				p.setPlayerTime(13000, true);
				sender.sendMessage(ChatColor.RED +  "Il fait nuit !");	
			}else{
				p.sendMessage(ChatColor.RED+ "Humm, ce continuum espace-temps n'est pas dans mes registres");
			}
			
		}
	
		
		
		
		
		
		return false;
	}
}
