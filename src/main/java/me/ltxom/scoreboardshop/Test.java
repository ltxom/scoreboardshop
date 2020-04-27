package me.ltxom.scoreboardshop;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {

		File f = new File("a.txt");
		FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(f);
		fileConfiguration.set("item","\n" +
				"      ==: org.bukkit.inventory.ItemStack\n" +
				"      v: 2230\n" +
				"      type: DIAMOND_SWORD\n" +
				"      meta:\n" +
				"        ==: ItemMeta\n" +
				"        meta-type: UNSPECIFIC\n" +
				"        display-name: 钻石剑aa\n" +
				"        enchants:\n" +
				"          LOOT_BONUS_MOBS: 3\n" +
				"        repair-cost: 1\n" +
				"    ");
		ItemStack itemStack = fileConfiguration.getItemStack("item");
		System.out.println();
	}
}
