package com.fusashy.has.event;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.fusashy.has.HAS;
import com.fusashy.has.game.GameState;

import net.md_5.bungee.api.ChatColor;

public class EntityAttackEvent implements Listener{
	
	@EventHandler
	public void onEntityAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player && ((e.getEntity() instanceof Horse) || (e.getEntity() instanceof Llama))) {
			e.setCancelled(true);
			e.getDamager().sendMessage(ChatColor.GOLD + "Qui vous à autorisé à taper "+ChatColor.RED + e.getEntity().getCustomName() + ChatColor.GOLD+ " ?");
		}
		
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player damager = (Player)e.getDamager();
			Player damaged = (Player)e.getEntity();
			if(HAS.getGame().hidersList.contains(damaged) && HAS.getGame().seekersList.contains(damager)) {
				if(HAS.getGame().state.equals(GameState.STARTED)) {
					HAS.getGame().hiderFound(damaged, damager);
				}
			}
			e.setCancelled(true);
		}
	}

}
