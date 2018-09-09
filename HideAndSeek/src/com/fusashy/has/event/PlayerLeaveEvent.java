package com.fusashy.has.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.fusashy.has.HAS;

public class PlayerLeaveEvent implements Listener{
	
	@EventHandler
	public void onPlayerJoin(org.bukkit.event.player.PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.GOLD + e.getPlayer().getName() + ChatColor.RED + " à quitté le serveur !");
		if(HAS.getInstance().vanish.containsKey(e.getPlayer())) {
			HAS.getInstance().vanish.remove(e.getPlayer());
		}
		
	
	}

}
