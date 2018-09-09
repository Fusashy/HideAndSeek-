package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;




public class ListCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((sender instanceof Player))
	    {
	      Player p = (Player)sender;
	      p.sendMessage(ChatColor.GRAY + "Liste des Joueurs connectés:");
	  
	      for(Player pp : Bukkit.getServer().getOnlinePlayers()) {
	    		  p.sendMessage(ChatColor.GRAY + pp.getName());
	    
	    	 
	      }
	      return false;
	    }
		return false;
	}
	
	

}
