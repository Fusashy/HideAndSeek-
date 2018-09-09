package com.fusashy.has.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadBuilder
{
  private String displayName;
  private String name;
  private int amount;
  private List<String> lore;
  private ItemStack head;
  private Map<Enchantment, Integer> enchantments;
  
  public String getDisplayName()
  {
    return this.displayName;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public ItemStack getHead() {
	  return this.head;
  }
  
  public int getAmount()
  {
    return this.amount;
  }
  
  public List<String> getLore()
  {
    return this.lore;
  }
  
  
  public Map<Enchantment, Integer> getEnchantments()
  {
    return this.enchantments;
  }
  
  public HeadBuilder(String name)
  {
    this.name = name;
    this.amount = 1;
  }
  
  public HeadBuilder(String name, int amount)
  {
    this.name = name;
    this.amount = amount;
  }
  
  public HeadBuilder withDisplayName(String displayName)
  {
    this.displayName = displayName;
    return this;
  }
  
  public HeadBuilder withAmount(int amount)
  {
    this.amount = amount;
    return this;
  }
  
  public HeadBuilder withLore(String[] lore)
  {
    this.lore = Arrays.asList(lore);
    return this;
  }
  
  public HeadBuilder withEnchant(Enchantment enchant, int level)
  {
    if (this.enchantments == null) {
      this.enchantments = new HashMap<Enchantment, Integer>();
    }
    this.enchantments.put(enchant, Integer.valueOf(level));
    return this;
  }
  
  @SuppressWarnings("deprecation")
public ItemStack build()
  {
    ItemStack item = new ItemStack(Material.SKULL_ITEM, this.amount, (short)SkullType.PLAYER.ordinal());
    SkullMeta meta = (SkullMeta)item.getItemMeta();
    meta.setLore(this.lore);
    meta.setOwner(this.name);
    meta.setDisplayName(this.displayName);
    if (this.enchantments != null) {
      for (Map.Entry<Enchantment, Integer> enchant : this.enchantments.entrySet()) {
        meta.addEnchant((Enchantment)enchant.getKey(), ((Integer)enchant.getValue()).intValue(), true);
      }
    }
    item.setItemMeta(meta);
     this.head = item;
    return item;
  }
}