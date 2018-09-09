package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPHereCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length <= 0) {
					sender.sendMessage(ChatColor.RED + "Veuillez ajouter le nom du joueur à cette commande.");
					return false;
				}
				if (args[0].equalsIgnoreCase("all")) {
					for (Player pp : Bukkit.getOnlinePlayers()) {
						if (!pp.equals(p)) {
							pp.teleport(p.getLocation());
							p.sendMessage(ChatColor.GOLD + "Vous avez téléporté tout le monde à vous.");
							pp.sendMessage(ChatColor.GOLD + "Vous avez été téléporté à " + ChatColor.RED + p.getName());
						}
					}
					return false;
				}
				String targetString = args[0];
				Player targetPlayer = Bukkit.getPlayer(targetString);
				if (targetPlayer != null) {
					if (!targetPlayer.equals(p)) {
						targetPlayer.teleport(p.getLocation());
						p.sendMessage(ChatColor.GOLD + "Vous avez téléporté " + ChatColor.RED + targetPlayer.getName()
								+ ChatColor.GOLD + " à vous.");
						targetPlayer.sendMessage(
								ChatColor.GOLD + "Vous avez été téléporté à " + ChatColor.RED + p.getName());
					} else {
						p.sendMessage(ChatColor.RED + "Pourquoi vous téléporter à vous même ?");
					}

				} else {
					p.sendMessage(ChatColor.RED + "Le joueur est introuvable.");
				}

			} else {
				sender.sendMessage("Cette commande est réservée aux joueurs.");
			}
			return false;

		} else {
			sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
			return false;
		}
	}
}
