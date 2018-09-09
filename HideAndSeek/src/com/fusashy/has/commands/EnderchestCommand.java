package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class EnderchestCommand implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length <= 0) {
				p.openInventory(p.getEnderChest());
			}else {
				
				String strPlayer = args[0];
				Player pp = Bukkit.getPlayer(strPlayer);
				if(pp != null) {
					p.openInventory(pp.getEnderChest());
				}else {
					p.sendMessage(ChatColor.RED + "Ce joueur n'existe pas.");
				}
				
			}
		
		}
		
		return false;
	}
}
