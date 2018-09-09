package com.fusashy.has.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import net.minecraft.server.v1_12_R1.WorldServer;

public class FakePlayer {

	MinecraftServer server;
	WorldServer world;
	public EntityPlayer npc;

	public FakePlayer(Location loc, String displayname) {
		server = ((CraftServer) Bukkit.getServer()).getServer();
		world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();

		Player target = Bukkit.getServer().getPlayer(displayname);

		if (target != null) {
			GameProfile playerProfile = new GameProfile(UUID.randomUUID(), displayname);
			setSkin(playerProfile, target.getUniqueId());
			npc = new EntityPlayer(server, world, playerProfile, new PlayerInteractManager(world));
		} else {
			@SuppressWarnings("deprecation")
			OfflinePlayer op = Bukkit.getServer().getOfflinePlayer(displayname);
			GameProfile playerProfile = new GameProfile(op.getUniqueId(), displayname);
			setSkin(playerProfile, op.getUniqueId());
			npc = new EntityPlayer(server, world, playerProfile, new PlayerInteractManager(world));
		}
		npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());

	}

	public static boolean setSkin(GameProfile profile, UUID uuid) {
		try {
			HttpsURLConnection connection = (HttpsURLConnection) new URL(
					String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false",
							UUIDTypeAdapter.fromUUID(uuid))).openConnection();
			if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
				String reply = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
				String skin = reply.split("\"value\":\"")[1].split("\"")[0];
				String signature = reply.split("\"signature\":\"")[1].split("\"")[0];
				profile.getProperties().put("textures", new Property("textures", skin, signature));
				return true;
			} else {
				System.out.println("Connection could not be opened (Response code " + connection.getResponseCode()
						+ ", " + connection.getResponseMessage() + ")");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void spawn() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) all).getHandle().playerConnection;
			connection.sendPacket(
					new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
	
		}

	}

}
