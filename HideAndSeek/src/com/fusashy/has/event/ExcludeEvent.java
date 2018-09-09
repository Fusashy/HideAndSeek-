package com.fusashy.has.event;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

import com.fusashy.has.HAS;
import com.fusashy.has.utils.HASPlayer;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;

@SuppressWarnings("deprecation")
public class ExcludeEvent implements Listener{
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(HAS.getInstance().exclude.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Vous avez été exclu. Vous ne pouvez plus bouger, parler ou utiliser les commandes.");
			e.getPlayer().setPlayerListName(ChatColor.BLACK + "[Exclu]" + e.getPlayer().getName());
			return;
		}
	
	
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			HASPlayer.setRank(p);
		}
		String str = e.getMessage();
		String newstr = str.replace("&a", ChatColor.GREEN + "").replace("&b", ChatColor.AQUA + "").replace("&c", ChatColor.RED + "");
		e.setMessage(newstr);
		if(HAS.getInstance().exclude.contains(e.getPlayer())) {
			if(e.getMessage().equals("24/07/2018")) {
				HAS.getInstance().exclude.remove(e.getPlayer());
				e.getPlayer().sendMessage(ChatColor.GOLD + "Vous avez été délivré des griffes du mal !");
				HAS.getInstance().exclude.remove(e.getPlayer());
				if(e.getPlayer().isOp()) {
					e.getPlayer().setPlayerListName(ChatColor.RED + "[Administrateur]" + e.getPlayer().getName());
				}else {
					e.getPlayer().setPlayerListName(e.getPlayer().getName());
				}
				e.getPlayer().setPlayerListName(e.getPlayer().getName());
				e.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
				e.setCancelled(true);
			}
			e.getPlayer().sendMessage(ChatColor.RED + "Vous avez été exclu. Vous ne pouvez plus bouger, parler ou utiliser les commandes.");
			e.setCancelled(true);
		}
	}

	
	@EventHandler
	public void onPlayerMove(PlayerCommandPreprocessEvent e) {
		if(HAS.getInstance().exclude.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Vous avez été exclu. Vous ne pouvez plus bouger, parler ou utiliser les commandes.");
		}
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			HASPlayer.setRank(p);
		}
		for (int i = 0; i < HAS.getInstance().npc.size(); i++) {
			PlayerConnection connection = ((CraftPlayer) e.getPlayer()).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,
					HAS.getInstance().npc.get(i)));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(HAS.getInstance().npc.get(i)));
			
		}
	}


}
