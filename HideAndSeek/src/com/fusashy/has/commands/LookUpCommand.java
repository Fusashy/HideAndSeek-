package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fusashy.has.utils.HASPlayer;

public class LookUpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.getName().equalsIgnoreCase("AppleMacOS")) {
			sender.sendMessage(ChatColor.RED + "Commande classifiée. Si vous la réutilisez vous serez banni.");
			return false;
		}
		if (sender instanceof Player) {
			if (args.length <= 0) {
				sender.sendMessage(ChatColor.RED + "Veuillez saisir le nom du joueur à lookup.");
				return false;
			}

			String strPlayer = args[0];
			Player bktPlayer = Bukkit.getPlayer(strPlayer);

			if (bktPlayer != null) {

				sender.sendMessage(ChatColor.RED + "------------LOOKUP------------");
				sender.sendMessage(ChatColor.GREEN + "Name : " + bktPlayer.getName());
				sender.sendMessage(ChatColor.GREEN + "UUID : " + bktPlayer.getUniqueId());
				sender.sendMessage(ChatColor.GREEN + "Ping : " + HASPlayer.getPing(bktPlayer));
				sender.sendMessage(
						ChatColor.GREEN + "IP Address : " + bktPlayer.getAddress().getAddress().getHostAddress());
				sender.sendMessage(ChatColor.GREEN + "Host : " + bktPlayer.getAddress().getAddress().getHostName());

				sender.sendMessage(ChatColor.RED + "--------------END-------------");
			} else {
				sender.sendMessage(ChatColor.RED + "Ce joueur est introuvable.");
			}

		}
		return false;
	}

}
