package com.fusashy.has.utils;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.fusashy.has.HAS;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;

public class HASPlayer {
	
	public static int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		return ep.ping;
	}
		
    public static void sendTitle(Player player, String msgTitle, String msgSubTitle, int ticks){
        IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + msgTitle + "\"}");
        IChatBaseComponent chatSubTitle = ChatSerializer.a("{\"text\": \"" + msgSubTitle + "\"}");
        PacketPlayOutTitle p = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle p2 = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(p);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(p2);
        sendTime(player, ticks);
}

private static void sendTime(Player player, int ticks){
        PacketPlayOutTitle p = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(p);
}

public static void sendActionBar(Player player, String message){
        IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(ppoc);
}



public static void setRank(Player p) {
	if(HAS.getInstance().exclude.contains(p)) {
		p.setPlayerListName(ChatColor.BLACK + "[Exclu]" + p.getName());
		return;
	}
	if(p.isOp()) {
		p.setPlayerListName(ChatColor.RED + "[Administrateur]" + p.getName());
	}else {
		p.setPlayerListName(ChatColor.WHITE + "[Unranked]" + p.getName());
	}

if (p.getName().equals("ConvertisseurYtb")) {
	p.setPlayerListName(ChatColor.AQUA + "[GueuleD'ange<3]" + p.getName());
}
if(p.getName().equals("Luwap")) {
	p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[ChériDeFusashy]" + p.getName());
}

if(p.getName().equals("Byni")) {
	p.setPlayerListName(ChatColor.GOLD + "[LeTémoin]" + p.getName());
}


if(HAS.getGame().hidersList.contains(p)) {
	p.setPlayerListName(ChatColor.BLUE + "[Hidder]" + p.getName());
}
if(HAS.getGame().seekersList.contains(p)) {
	p.setPlayerListName(ChatColor.RED + "[Seeker]" + p.getName());
}
if(HAS.getGame().neutral.contains(p)) {
	p.setPlayerListName(ChatColor.GRAY + "[Joueur]" + p.getName());
}
if(HAS.getGame().admin != null) {
	if(HAS.getGame().admin.equals(p)) {
		p.setPlayerListName(ChatColor.DARK_RED + "[GameAdmin]" + p.getName());
	}
}

}
}
