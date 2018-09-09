package com.fusashy.has.event;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.fusashy.has.HAS;
import com.fusashy.has.game.GameState;
import com.fusashy.has.utils.HASPlayer;

import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class PlayerJoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.GOLD + e.getPlayer().getName() + ChatColor.RED + " à rejoint le serveur !");
		HAS.getInstance().vanish.put(e.getPlayer(), false);
		HASPlayer.setRank(e.getPlayer());
		e.getPlayer().setGameMode(GameMode.SURVIVAL);
		
		if (HAS.getGame().state.equals(GameState.STARTED) && !e.getPlayer().isOp()) {
			e.getPlayer().sendMessage(
					HAS.getInstance().plugin_prefix + "La partie à déjà commencée, vous êtes en mode spectateur !");
			e.getPlayer().setGameMode(GameMode.SPECTATOR);
		}
		for (int i = 0; i < HAS.getInstance().npc.size(); i++) {
			PlayerConnection connection = ((CraftPlayer) e.getPlayer()).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,
					HAS.getInstance().npc.get(i)));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(HAS.getInstance().npc.get(i)));
			
		}

	}

}
