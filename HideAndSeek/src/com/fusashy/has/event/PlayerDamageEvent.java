package com.fusashy.has.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageEvent implements Listener{
	
	@EventHandler
	public void onDamageEvent(EntityDamageEvent e) {
		e.setCancelled(true);
	}

}
