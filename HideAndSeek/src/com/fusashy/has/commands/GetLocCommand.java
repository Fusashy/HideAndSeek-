package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fusashy.has.HAS;

public class GetLocCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player p = (Player) sender;
			Location loc = p.getLocation();
			p.sendMessage(ChatColor.RED +"Votre localisation actuelle est " + ChatColor.GOLD
					+ "X : " + (int) loc.getX() + " Y : " + (int) loc.getY() + " Z : " + (int) loc.getZ());
			Bukkit.getScheduler().runTaskLater(HAS.getInstance(), new Runnable() {

				@Override
				public void run() {
					p.sendMessage(ChatColor.RED + "(Sinon, il y'avait F3 ..)");

				}
			}, 100);

		} else {
			sender.sendMessage("Console, tu n'es pas un joueur ! Ta localisation c'est le void.");
		}
		return false;
	}
}
