package com.fusashy.has.game;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.fusashy.has.HAS;
import com.fusashy.has.utils.HASPlayer;

public class HideAndSeek {

	public GameState state;
	public int cooldown;
	public int time;
	public ArrayList<Player> hidersList = new ArrayList<>();
	public ArrayList<Player> neutral = new ArrayList<>();
	public ArrayList<Player> seekersList = new ArrayList<>();
	public Location seekers;
	public Location hiders;
	public int stillHiders;
	public Player admin;
	public int task;
	public int main;

	public HideAndSeek() {
		state = GameState.LOBBY;
		cooldown = 30;
		time = 900;
		seekers = new Location(Bukkit.getWorlds().get(0), 161, 66, 528);
		hiders = new Location(Bukkit.getWorlds().get(0), 294.5, 65.5, 719.5);
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public GameState changeGamestate(GameState state) {
		this.state = state;
		return state;
	}

	public void start() {

		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(HAS.getInstance(), new Runnable() {

			@Override
			public void run() {
				HAS.getGame().state = GameState.STARTING;
				for (Player p : Bukkit.getOnlinePlayers()) {
					HASPlayer.sendTitle(p, ChatColor.YELLOW + "La partie commence dans : ",
							ChatColor.GOLD + "" + cooldown + " secondes", 20);
					if (cooldown == 0) {
						HAS.getGame().state = GameState.STARTED;
						stillHiders = hidersList.size();
						mainWhile();
						p.teleport(p.getWorld().getSpawnLocation());
						for (int i = 0; i < hidersList.size(); i++) {
							hidersList.get(i).teleport(hiders);
							hidersList.get(i).setGameMode(GameMode.SURVIVAL);
							hidersList.get(i).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50, 5));
						}
						for (int i = 0; i < seekersList.size(); i++) {
							seekersList.get(i).teleport(seekers);
							seekersList.get(i).setGameMode(GameMode.SURVIVAL);
							seekersList.get(i).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 5));
							seekersList.get(i).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 5));
						}
						for (int i = 0; i < neutral.size(); i++) {
							neutral.get(i).setGameMode(GameMode.SPECTATOR);
							neutral.get(i).teleport(hiders);
						}
						for (Player pp : Bukkit.getOnlinePlayers()) {
							pp.getInventory().clear();
						}
						Bukkit.getScheduler().cancelTask(task);

					}
				}

				cooldown--;
			}
		}, 0L, 20L);
	}

	public void mainWhile() {
		main = Bukkit.getScheduler().scheduleSyncRepeatingTask(HAS.getInstance(), new Runnable() {

			@Override
			public void run() {
				if (stillHiders == 0) {
					
					Bukkit.getScheduler().runTaskLater(HAS.getInstance(), new Runnable() {

						@Override
						public void run() {
							stop(1);
							Bukkit.getScheduler().cancelTask(main);
						}
					}, 60);
				}
				if (time == 0 && stillHiders > 0) {
					
					Bukkit.getScheduler().runTaskLater(HAS.getInstance(), new Runnable() {

						@Override
						public void run() {
							stop(0);
							Bukkit.getScheduler().cancelTask(main);
						}
					}, 60);

				}

				cooldown--;
			}
		}, 0L, 20L);
	}

	public void hiderFound(Player hider, Player seeker) {
		Bukkit.broadcastMessage(ChatColor.BLUE + hider.getName() + ChatColor.RED + " à été trouvé par " + ChatColor.RED
				+ seeker.getName());
		stillHiders--;
		hider.setGameMode(GameMode.SPECTATOR);
		hider.teleport(hiders);
	}

	public void stop(int winner) {
		if(winner == 1) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				HASPlayer.sendTitle(p, ChatColor.YELLOW + "Les chercheurs ont gagné !", "Fin de la partie !",
						20);
			}
		}else if(winner == 0) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				HASPlayer.sendTitle(p, ChatColor.YELLOW + "Les hidders ont gagné !", "Fin de la partie !",
						20);
			}
		}else {
			
		}
		if (!HAS.getGame().state.equals(GameState.LOBBY)) {
			Bukkit.broadcastMessage(HAS.getInstance().plugin_prefix
					+ "La partie est terminée ou à été arrêtée par un administrateur !");
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.equals(admin)) {
					p.teleport(admin.getWorld().getSpawnLocation());
					p.setGameMode(GameMode.SURVIVAL);
					p.removePotionEffect(PotionEffectType.GLOWING);
				}
			}
			HAS.resetGame();
		} else {

		}

	}

}
