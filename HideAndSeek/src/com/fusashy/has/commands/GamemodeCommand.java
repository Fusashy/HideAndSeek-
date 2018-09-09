package com.fusashy.has.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			if (args.length <= 0) {
				sender.sendMessage(ChatColor.RED + "Veuillez ajouter des arguments à cette commande.");
				return false;
			}
			if (args[0].equalsIgnoreCase("0")) {
				p.setGameMode(GameMode.SURVIVAL);
				sender.sendMessage(ChatColor.GOLD + "Votre gamemode à été passé en mode survie.");
				return false;
			}
			if (args[0].equalsIgnoreCase("1")) {
				p.setGameMode(GameMode.CREATIVE);
				sender.sendMessage(ChatColor.GOLD + "Votre gamemode à été passé en mode créatif.");
				return false;
			}
			if (args[0].equalsIgnoreCase("2")) {
				p.setGameMode(GameMode.ADVENTURE);
				sender.sendMessage(ChatColor.GOLD + "Votre gamemode à été passé en mode aventure.");
				return false;
			}
			if (args[0].equalsIgnoreCase("3")) {
				p.setGameMode(GameMode.SPECTATOR);
				sender.sendMessage(ChatColor.GOLD + "Votre gamemode à été passé en mode spectateur.");
				return false;
			}
			sender.sendMessage(ChatColor.RED + "Humm, ce gamemode existe ?");
			return false;
		}
		sender.sendMessage(ChatColor.DARK_RED + "Hey la console, t'es pas un joueur ok ?");

	} else {
		sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
	}
		return false;
	}
}
