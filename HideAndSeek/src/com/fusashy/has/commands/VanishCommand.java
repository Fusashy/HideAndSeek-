package com.fusashy.has.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fusashy.has.HAS;
import com.fusashy.has.game.GameState;



public class VanishCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!(HAS.getInstance().vanish.containsKey(p))) {
				HAS.getInstance().vanish.put(p, false);
			}
			if(HAS.getInstance().vanish.get(p) == true) {
				for(Player pp : Bukkit.getServer().getOnlinePlayers()) {
					pp.showPlayer(p);
				}
				HAS.getInstance().vanish.put(p, false);
				p.sendMessage(ChatColor.GREEN + "Vous êtes désormais visible par les autres joueurs.");
			}else {
				for(Player pp : Bukkit.getServer().getOnlinePlayers()) {
					pp.hidePlayer(p);
				}
				
				HAS.getInstance().vanish.put(p, true);
				p.sendMessage( ChatColor.GREEN + "Vous êtes désormais invisible par les autres joueurs.");
			}
			
			
		
		}
		

	} else {
		if(HAS.getGame().state.equals(GameState.STARTED) && !sender.equals(HAS.getGame().admin)) {
			sender.sendMessage(ChatColor.RED + "Utiliser cette commande pendant la partie est considéré comme de la triche. Si vous recommencez vous serez exclu.");
		}
		sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission");
	}
		return false;
	}
}
