package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fusashy.has.utils.HeadBuilder;

public class HeadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length <= 0) {

					HeadBuilder builder = new HeadBuilder(p.getName());
					Bukkit.broadcastMessage("" + p.getInventory().firstEmpty());

					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED + "Votre inventaire est plein.");
						return false;
					}

					p.getInventory().addItem(builder.build());
					p.sendMessage(ChatColor.GOLD + "Vous avez reçu votre tête !");
				} else {

					String strPlayer = args[0];

					HeadBuilder builder = new HeadBuilder(strPlayer);
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED + "Votre inventaire est plein.");
						return false;
					}

					p.getInventory().addItem(builder.build());
					p.sendMessage(ChatColor.GOLD + "Vous avez reçu la tête de " + ChatColor.RED + strPlayer);
				}

			}

		} else {
			sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
		}
		return false;
	}

}
