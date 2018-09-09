package com.fusashy.has;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.fusashy.has.commands.BroadcastCommand;
import com.fusashy.has.commands.EnderchestCommand;
import com.fusashy.has.commands.ExcludeCommand;
import com.fusashy.has.commands.FeedCommand;
import com.fusashy.has.commands.GamemodeCommand;
import com.fusashy.has.commands.GetLocCommand;
import com.fusashy.has.commands.HeadCommand;
import com.fusashy.has.commands.HealCommand;
import com.fusashy.has.commands.InvSeeCommand;
import com.fusashy.has.commands.KickCommand;
import com.fusashy.has.commands.ListCommand;
import com.fusashy.has.commands.LookUpCommand;
import com.fusashy.has.commands.NickCommand;
import com.fusashy.has.commands.RespawnCommand;
import com.fusashy.has.commands.SendSpawnCommand;
import com.fusashy.has.commands.SetSpawnCommand;
import com.fusashy.has.commands.SpawnCommand;
import com.fusashy.has.commands.TPHereCommand;
import com.fusashy.has.commands.TimeCommand;
import com.fusashy.has.commands.VanishCommand;
import com.fusashy.has.event.BlockBreakEvent;
import com.fusashy.has.event.BlockPlaceEvent;
import com.fusashy.has.event.EntityAttackEvent;
import com.fusashy.has.event.ExcludeEvent;
import com.fusashy.has.event.PlayerDamageEvent;
import com.fusashy.has.event.PlayerJoinEvent;
import com.fusashy.has.event.PlayerLeaveEvent;
import com.fusashy.has.game.GameCommand;
import com.fusashy.has.game.HideAndSeek;
import com.fusashy.has.utils.FakePlayer;

import net.minecraft.server.v1_12_R1.EntityPlayer;




public class HAS extends JavaPlugin{
	
	private static HAS instance;
	private static HideAndSeek game;
	public ArrayList<Player> exclude = new ArrayList<>();
	public ArrayList<EntityPlayer> npc = new ArrayList<>();
	public HashMap<Player, Boolean> vanish = new HashMap<Player, Boolean>();
	public String plugin_prefix = ChatColor.RED + "[" +ChatColor.AQUA + "HideAndSeek" + ChatColor.RED + "]";
	
	@Override
	public void onEnable() {
		instance = this;
		game = new HideAndSeek();
		registerEvents();
		registerCommands();
	//	summonNPC();
		super.onEnable();
		
	}
	
	@Override
	public void onDisable() {
		
		super.onDisable();
	}
	
	public static HAS getInstance() {return instance;}
	
	void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new ExcludeEvent(), this);
		this.getServer().getPluginManager().registerEvents(new EntityAttackEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDamageEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
		this.getServer().getPluginManager().registerEvents(new BlockBreakEvent(), this);
		this.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
	}
	
	void registerCommands() {
		this.getCommand("game").setExecutor(new GameCommand());
		this.getCommand("exclude").setExecutor(new ExcludeCommand());
		this.getCommand("lookup").setExecutor(new LookUpCommand());
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("gm").setExecutor(new GamemodeCommand());
		this.getCommand("gamemode").setExecutor(new GamemodeCommand());
		this.getCommand("getloc").setExecutor(new GetLocCommand());
		this.getCommand("heal").setExecutor(new HealCommand());
		this.getCommand("invsee").setExecutor(new InvSeeCommand());
		this.getCommand("sendspawn").setExecutor(new SendSpawnCommand());
		this.getCommand("kick").setExecutor(new KickCommand());
		this.getCommand("bc").setExecutor(new BroadcastCommand());
		this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
		this.getCommand("respawn").setExecutor(new RespawnCommand());
		this.getCommand("spawn").setExecutor(new SpawnCommand());
		this.getCommand("nick").setExecutor(new NickCommand());
		this.getCommand("time").setExecutor(new TimeCommand());
		this.getCommand("tphere").setExecutor(new TPHereCommand());
		this.getCommand("vanish").setExecutor(new VanishCommand());
		this.getCommand("feed").setExecutor(new FeedCommand());
		this.getCommand("list").setExecutor(new ListCommand());
		this.getCommand("head").setExecutor(new HeadCommand());
		this.getCommand("enderchest").setExecutor(new EnderchestCommand());
		this.getCommand("ec").setExecutor(new EnderchestCommand());
	}
	
	void summonNPC() {
		FakePlayer ziel = new FakePlayer(new Location(Bukkit.getWorlds().get(0), 7.5, 101, -6.5, 180, 0), "Luwap");
		ziel.npc.getBukkitEntity().setPlayerListName(ChatColor.GRAY + "[NPC]" + ziel.npc.getBukkitEntity().getEntityId());
		ziel.npc.getBukkitEntity().setItemInHand(new ItemStack(Material.SAPLING));
		ziel.spawn();
		npc.add(ziel.npc);
		
		FakePlayer elura = new FakePlayer(new Location(Bukkit.getWorlds().get(0), 7.5, 101, 7.5, -90, 0), "EluraRm");
		elura.npc.getBukkitEntity().setPlayerListName(ChatColor.GRAY + "[NPC]" + elura.npc.getBukkitEntity().getEntityId());
		elura.spawn();
		npc.add(elura.npc);
		
		FakePlayer aywen = new FakePlayer(new Location(Bukkit.getWorlds().get(0), 0.5, 99.5, -3.5, 0, 0), "Aywen");
		aywen.npc.getBukkitEntity().setPlayerListName(ChatColor.GRAY + "[NPC]" + aywen.npc.getBukkitEntity().getEntityId());
		aywen.spawn();
		npc.add(aywen.npc);
		
		FakePlayer nyandark = new FakePlayer(new Location(Bukkit.getWorlds().get(0), -6.5, 101, 7.5, 180, 0), "nyandark06");
		nyandark.npc.getBukkitEntity().setPlayerListName(ChatColor.GRAY + "[NPC]" + nyandark.npc.getBukkitEntity().getEntityId());
		ziel.npc.getBukkitEntity().setItemInHand(new ItemStack(Material.WOOD));
		nyandark.spawn();
		npc.add(nyandark.npc);
		
		FakePlayer fusashy = new FakePlayer(new Location(Bukkit.getWorlds().get(0), -6.5, 101, -6.5, -90, 0), "AppleMacOS");
		fusashy.npc.getBukkitEntity().setPlayerListName(ChatColor.GRAY + "[NPC]" + fusashy.npc.getBukkitEntity().getEntityId());
		fusashy.spawn();
		npc.add(fusashy.npc);
		
	}
	
	public static HideAndSeek getGame() {
		return game;
	}
	
	public static void resetGame() {
		game = new HideAndSeek();
	}

}
