package me.ltxom.scoreboardshop.entity;

import org.bukkit.inventory.ItemStack;

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

	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	public String getItemCommand() {
		return itemCommand;
	}

	public void setItemCommand(String itemCommand) {
		this.itemCommand = itemCommand;
	}
}
