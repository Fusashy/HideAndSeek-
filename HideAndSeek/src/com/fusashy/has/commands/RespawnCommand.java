package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class RespawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		if (sender instanceof Player) {
			if (args.length <= 0) {
				sender.sendMessage(ChatColor.RED + "Veuillez saisir le nom du joueur à rendre la vie.");
				return false;
			}

			String strPlayer = args[0];
			Player bktPlayer = Bukkit.getPlayer(strPlayer);

			if (bktPlayer != null) {
				sender.sendMessage(ChatColor.GOLD + "Ce joueur à été ramené à la vie.");
				bktPlayer.spigot().respawn();
			} else {
				sender.sendMessage(ChatColor.RED + "Ce joueur est introuvable.");
			}

		}
		return false;
	}
	
	

}
