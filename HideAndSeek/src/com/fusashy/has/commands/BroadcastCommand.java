package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {

			String str = "";

			for (String arg : args) {
				str += arg + " ";
			}

			if (args.length <= 0) {
				sender.sendMessage(ChatColor.RED + "Avec un message c'est mieux, non ?");
				return false;
			}

			Bukkit.broadcastMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "Broadcast" + ChatColor.DARK_RED + "]"
					+ ChatColor.RED + str);

		} else {
			sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
		}
		return false;
	}
}
