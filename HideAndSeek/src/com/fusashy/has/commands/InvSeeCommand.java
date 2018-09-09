package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class InvSeeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
		if ((sender instanceof Player))
	    {
	      Player p = (Player)sender;
	      if (args.length <= 0)
	      {
	        p.sendMessage(ChatColor.RED +"Veuillez saisir le pseudo du joueur à qui vous souhaitez ouvrir l'inventaire.");
	        return false;
	      }
	      String targetString = args[0];
	      Player targetPlayer = Bukkit.getPlayer(targetString);
	      if (targetPlayer != null) {
	    	  if(targetPlayer != p) {
	    		     p.openInventory(targetPlayer.getInventory());
	    	  }else {
	    		  p.sendMessage(ChatColor.RED +"Pourquoi ouvrir votre inventaire ? (:");
	    	  }
	   
	      } else {
	        p.sendMessage(ChatColor.RED +"Le joueur est introuvable.");
	      }
	    }else {
	    	sender.sendMessage("How am i supposed to open an inventory on a console ? ");
	    }

	} else {
		sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
	}
		return false;
	}
}
