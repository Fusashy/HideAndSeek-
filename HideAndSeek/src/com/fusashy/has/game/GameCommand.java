package com.fusashy.has.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.fusashy.has.HAS;
import com.fusashy.has.utils.HASPlayer;


public class GameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))return false;
		HASPlayer.setRank((Player) sender);
		if (args.length <= 0) {
			sender.sendMessage(HAS.getInstance().plugin_prefix + "Cette commande doit être suivie d'arguments.");
			return false;
		}

		if (args[0].equalsIgnoreCase("defineplayers")) {
			if (HAS.getGame().admin == null) {
				sender.sendMessage(
						HAS.getInstance().plugin_prefix + "L'administrateur de la partie n'a pas encore été défini !");
			} else {
			}
			return true;
		}

		if (args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "Liste des commandes disponibles : ");
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "   - /game start");
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "   - /game stop");
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "   - /game state");
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "   - /game sethider pseudo");
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "   - /game setseeker pseudo");
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "   - /game cooldown <seconds>");
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "   - /game admin");
			return true;
		}

		if (args[0].equalsIgnoreCase("start")) {
			if (HAS.getGame().admin == null) {
				sender.sendMessage(HAS.getInstance().plugin_prefix + "L'administrateur de jeu n'a pas été défini !");
				return false;
			} else if (HAS.getGame().seekersList.size() < 1) {
				sender.sendMessage(
						HAS.getInstance().plugin_prefix + "Il y'a trop peu de Seekers pour commencer la partie !");
				return false;
			} else if (HAS.getGame().hidersList.size() < 1) {
				sender.sendMessage(
						HAS.getInstance().plugin_prefix + "Il y'a trop peu de Hiders pour commencer la partie !");
				return false;
			} else if (HAS.getGame().state.equals(GameState.STARTING)) {
				sender.sendMessage(HAS.getInstance().plugin_prefix + "La partie est en train de commencer");
				return false;
			} else if (HAS.getGame().state.equals(GameState.STARTED)) {
				sender.sendMessage(HAS.getInstance().plugin_prefix + "La partie à déjà commencé");
				return false;
			}

			HAS.getGame().start();
			return true;
		}

		if (args[0].equalsIgnoreCase("stop")) {
			if (HAS.getGame().state.equals(GameState.STARTED)) {
				Bukkit.broadcastMessage(HAS.getInstance().plugin_prefix + sender.getName() + ChatColor.GOLD
						+ " à mit fin à la partie.");
				HAS.getGame().stop(999);
				return true;
			} else {
				sender.sendMessage(HAS.getInstance().plugin_prefix + "La partie n'a pas encore commencée.");
			}

		}

		if (args[0].equalsIgnoreCase("cooldown")) {
			if (args[1] == null) {
				sender.sendMessage(HAS.getInstance().plugin_prefix + "Cette commande doit être suivie d'arguments.");

				return false;
			} else {
				if (HAS.getGame().state.equals(GameState.LOBBY)) {
					int i = Integer.parseInt(args[1]);
					HAS.getGame().cooldown = i;
					sender.sendMessage(HAS.getInstance().plugin_prefix + "Le cooldown de la partie a été défini à : "
							+ i + " secondes.");
				} else {
					sender.sendMessage(HAS.getInstance().plugin_prefix + "La partie est en train de commencer.");
				}

				return true;
			}

		}
		
		if (args[0].equalsIgnoreCase("sethider")) {
			if (args[1] == null) {
				sender.sendMessage(HAS.getInstance().plugin_prefix + "Cette commande doit être suivie d'arguments.");

				return false;
			} else {
				if (HAS.getGame().state.equals(GameState.LOBBY)) {
					Player bk = Bukkit.getPlayer(args[1]);
					if(bk != null) {
						if(HAS.getGame().neutral.contains(bk)) {
							HAS.getGame().neutral.remove(bk);
							HAS.getGame().hidersList.add(bk);
							if(HAS.getGame().seekersList.contains(bk)) {
								HAS.getGame().seekersList.remove(bk);
							}
							sender.sendMessage(HAS.getInstance().plugin_prefix + "Ce joueur est maintenant Hider");
						}
					}else {
						sender.sendMessage(HAS.getInstance().plugin_prefix + "Joueur introuvable");
					}
				} else {
					sender.sendMessage(HAS.getInstance().plugin_prefix + "La partie est en train de commencer.");
				}

				return true;
			}

		}
		
		if (args[0].equalsIgnoreCase("setseeker")) {
			if (args[1] == null) {
				sender.sendMessage(HAS.getInstance().plugin_prefix + "Cette commande doit être suivie d'arguments.");

				return false;
			} else {
				if (HAS.getGame().state.equals(GameState.LOBBY)) {
					Player bk = Bukkit.getPlayer(args[1]);
					if(bk != null) {
						if(HAS.getGame().neutral.contains(bk)) {
							HAS.getGame().neutral.remove(bk);
							HAS.getGame().seekersList.add(bk);
							if(HAS.getGame().hidersList.contains(bk)) {
								HAS.getGame().hidersList.remove(bk);
							}
							sender.sendMessage(HAS.getInstance().plugin_prefix + "Ce joueur est maintenant Seeker");
						}
					}else {
						sender.sendMessage(HAS.getInstance().plugin_prefix + "Joueur introuvable");
					}
				} else {
					sender.sendMessage(HAS.getInstance().plugin_prefix + "La partie est en train de commencer.");
				}

				return true;
			}

		}


		// accès au admin panel
		if (args[0].equalsIgnoreCase("admin")) {
			if (!sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission.");
				return false;
			}
			if (args[1] != null) {
				Player b = Bukkit.getPlayer(args[1]);
				if (b == null) {
					sender.sendMessage(HAS.getInstance().plugin_prefix + "Ce joueur est introuvable.");
					return false;
				} else {
					if (b.equals(HAS.getGame().admin)) {
						sender.sendMessage(
								HAS.getInstance().plugin_prefix + "Vous êtes déjà l'administrateur de la partie !");
						return false;
					}
					Bukkit.broadcastMessage(HAS.getInstance().plugin_prefix + ChatColor.RED + b.getName()
							+ " est désormais l'administrateur de la partie.");
					HAS.getGame().admin = b;
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							p.setOp(false);
						}
						if(!p.equals(HAS.getGame().admin)) {
							HAS.getGame().neutral.add(p);
						}
					}
					b.setOp(true);
				}
			} else {
				if (sender.equals(HAS.getGame().admin)) {
				
				} else {
					sender.sendMessage(HAS.getInstance().plugin_prefix + "Vous n'avez pas la permission !");
					return false;
				}
			}

			return true;
		}

		if (args[0].equalsIgnoreCase("state")) {
			sender.sendMessage(HAS.getInstance().plugin_prefix + ChatColor.GOLD + "Status de la partie : "
					+ ChatColor.RED + HAS.getGame().state);
			return true;
		}
		
		if (args[0].equalsIgnoreCase("glow")) {
			for(int i = 0; i < HAS.getGame().hidersList.size(); i++) {
				HAS.getGame().hidersList.get(i).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 15, 5));
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("timer")) {
			sender.sendMessage(HAS.getGame().time + "");
			return true;
		}

		return false;
	}

}
