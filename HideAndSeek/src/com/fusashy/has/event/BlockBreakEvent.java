package com.fusashy.has.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class BlockBreakEvent implements Listener{
	
	@EventHandler
	public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Vous n'êtes pas autorisé à casser ce bloc.");
		}
	}

}
