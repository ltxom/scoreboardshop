package me.ltxom.scoreboardshop.entity;

import com.google.gson.Gson;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShopItem {
	private String displayName;
	private String scoreboardVarName;
	private String lore;
	private String itemType;
	private String categoryName;
	private Double price;
	private ItemStack itemStack;
	private String itemCommand;
	private String material;

	private FileConfiguration fileConfiguration = new YamlConfiguration();

	public ShopItem() {
	}

	public ShopItem(String string, ItemStack itemStack) {
		if (string.startsWith("ShopItem{")) {
			displayName = string.split("displayName='")[1].split("'")[0];
			scoreboardVarName = string.split(", scoreboardVarName='")[1].split("'")[0];
			lore = string.split(", lore='")[1].split("'")[0];
			itemType = string.split(", itemType='")[1].split("'")[0];
			categoryName = string.split(", categoryName='")[1].split("'")[0];
			price = Double.parseDouble(string.split(", price=")[1].split(",")[0]);
			itemCommand = string.split(", itemCommand='")[1].split("'")[0];
			material = string.split(", material='")[1].split("'")[0];
			this.itemStack = itemStack;
		}
	}

	@Override
	public String toString() {
		return "ShopItem{" +
				"displayName='" + displayName + '\'' +
				", scoreboardVarName='" + scoreboardVarName + '\'' +
				", lore='" + lore + '\'' +
				", itemType='" + itemType + '\'' +
				", categoryName='" + categoryName + '\'' +
				", price=" + price +
				", itemCommand='" + itemCommand + '\'' +
				", material='" + material + '\'' +
				'}';
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}


	public String getScoreboardVarName() {
		return scoreboardVarName;
	}

	public void setScoreboardVarName(String scoreboardVarName) {
		this.scoreboardVarName = scoreboardVarName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public void setItemStack(ItemStack itemStack, Integer itemNumber) {
		itemStack = itemStack.clone();
		itemStack.setAmount(itemNumber);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(displayName);
		List list = new ArrayList();
		for (String str : lore.split("\n")) {
			list.add(str);
		}
		itemMeta.setLore(list);
		itemStack.setItemMeta(itemMeta);
		this.itemStack = itemStack;
	}

	public String getItemCommand() {
		return itemCommand;
	}

	public void setItemCommand(String itemCommand) {
		this.itemCommand = itemCommand;
	}
}
