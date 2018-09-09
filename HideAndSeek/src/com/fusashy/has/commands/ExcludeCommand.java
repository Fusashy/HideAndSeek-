package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.fusashy.has.HAS;


public class ExcludeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
		if(args.length <= 0) {
			sender.sendMessage(ChatColor.RED + "Vous devez ajouter un argument à cete commande.");
			return false;
		}


		Player bkt = Bukkit.getPlayer(args[0]);
		if(bkt != null) {
			if(HAS.getInstance().exclude.contains(bkt)) {
				sender.sendMessage(ChatColor.GOLD + "Vous avez libéré " +ChatColor.RED+ bkt.getName());
				bkt.sendMessage(ChatColor.GOLD + "Vous avez été libéré " +ChatColor.RED+ sender.getName());
				HAS.getInstance().exclude.remove(bkt);
				if(bkt.isOp()) {
					bkt.setPlayerListName(ChatColor.RED + "[Administrateur]" + bkt.getName());
				}else {
					bkt.setPlayerListName(bkt.getName());
				}
				bkt.setPlayerListName(bkt.getName());
				bkt.removePotionEffect(PotionEffectType.BLINDNESS);
			}else {
				sender.sendMessage(ChatColor.GOLD + "Vous avez exclu " +ChatColor.RED+ bkt.getName());
				bkt.sendMessage(ChatColor.GOLD + "Vous avez été exclu par " +ChatColor.RED+ sender.getName());
				HAS.getInstance().exclude.add(bkt);
				bkt.setPlayerListName(ChatColor.BLACK + "[Exclu]" + bkt.getName());
				bkt.teleport(bkt.getWorld().getSpawnLocation());
				bkt.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 255));
			}
		}

	} else {
		sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
	}
		return false;
	}
}
