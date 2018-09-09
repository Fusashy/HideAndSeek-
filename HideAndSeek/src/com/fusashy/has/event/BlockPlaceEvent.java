package com.fusashy.has.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class BlockPlaceEvent implements Listener{
	
	@EventHandler
	public void onBlockBreak(org.bukkit.event.block.BlockPlaceEvent e) {
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Vous n'êtes pas autorisé à placer ce bloc.");
		}
	}

}
