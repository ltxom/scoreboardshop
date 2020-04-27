package me.ltxom.scoreboardshop.service;

import me.ltxom.scoreboardshop.entity.ShopItem;
import me.ltxom.scoreboardshop.util.ResultCode;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ShopService {
	private FileConfiguration scoreboardConfig;
	private ScoreboardManager scoreboardManager;
	private File scoreboardConfigFile;
	private FileConfiguration languageConfig;
	private FileConfiguration shopConfig;
	private File shopConfigFile;

	public ShopService(FileConfiguration scoreboardConfig, ScoreboardManager scoreboardManager,
					   File scoreboardConfigFile, FileConfiguration languageConfig, FileConfiguration shopConfig,
					   File shopConfigFile) {
		this.scoreboardConfig = scoreboardConfig;
		this.scoreboardManager = scoreboardManager;
		this.scoreboardConfigFile = scoreboardConfigFile;
		this.languageConfig = languageConfig;
		this.shopConfig = shopConfig;
		this.shopConfigFile = shopConfigFile;
	}

	public ResultCode createCategory(String categoryName, String displayName, String displayItem) {
		if (shopConfig.contains(categoryName)) {
			return ResultCode.EXIST;
		}
		shopConfig.set(categoryName + ".display_name", displayName);
		shopConfig.set(categoryName + ".display_item", displayItem);
		return ResultCode.saveConfigWithResultCode(shopConfig, shopConfigFile);
	}

	public ResultCode removeCategory(String categoryName) {
		if (!shopConfig.contains(categoryName)) {
			return ResultCode.DNE;
		}
		shopConfig.set(categoryName, null);
		try {
			shopConfig.save(shopConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultCode.CODE_OK;
	}

	public void listCategories(CommandSender sender) {
		Set<String> keys = shopConfig.getKeys(false);
		for (String key : keys) {
			String displayName = shopConfig.get(key + ".display_name").toString();
			TextComponent textComponent =
					new TextComponent("§a" + languageConfig.get("category-name") + ": §b" + key +
							"    §a" + languageConfig.get("display-name") + ": §r" + displayName
					);
			sender.spigot().sendMessage(textComponent);
		}
	}

	public ResultCode createItem(CommandSender sender, String categoryName, String scoreboardVarName, Double price,
								 String itemDisplayName,
								 String itemLore, String itemType, Integer itemNumber, String material,
								 String itemCommand) {
		if (!shopConfig.contains(categoryName)) {
			return ResultCode.CATEGORY_DNE;
		}
		if (!scoreboardConfig.contains(scoreboardVarName)) {
			return ResultCode.SCOREBOARD_DNE;
		}
		if (price < 0) {
			return ResultCode.ILLGEAL_ARG;
		}


		String nextItemIndex;
		Set<String> keys = shopConfig.getKeys(true);

		int counter = 1;
		while (keys.contains(categoryName + ".items.item-" + counter)) {
			counter++;
		}
		nextItemIndex = "item-" + counter;
		ShopItem shopItem = new ShopItem();
		shopItem.setCategoryName(categoryName);
		shopItem.setDisplayName(itemDisplayName.replace("_", " "));
		shopItem.setScoreboardVarName(scoreboardVarName);
		shopItem.setPrice(price);
		shopItem.setLore(itemLore.replace("_", " "));
		shopItem.setItemType(itemType);
		if (itemType.toLowerCase().equals("hand")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack itemStack = player.getInventory().getItemInMainHand();
				shopItem.setLore(shopItem.getLore() + "__ §d" + languageConfig.get("price") + ": §e" + price + scoreboardConfig.get(scoreboardVarName) + "__ §d" + languageConfig.get("amount") + ": §e" + itemNumber);
				shopItem.setItemStack(itemStack, itemNumber);
			} else {
				return ResultCode.ERROR;
			}
		} else if (itemType.toLowerCase().equals("command")) {
			if (Material.getMaterial(material) == null) {
				return ResultCode.DNE;
			}
			shopItem.setItemCommand(itemCommand);
			shopItem.setMaterial(material);
			shopItem.setLore(shopItem.getLore() + "__ §d" + languageConfig.get("price") + ": §e" + price + scoreboardConfig.get(scoreboardVarName));

		} else {
			return ResultCode.ILLGEAL_ARG;
		}
		shopConfig.set((categoryName + ".items." + nextItemIndex + ".shopitem"), shopItem.toString());
		shopConfig.set((categoryName + ".items." + nextItemIndex + ".itemstack"), shopItem.getItemStack());

		try {
			shopConfig.save(shopConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultCode.CODE_OK;
	}

	public ResultCode listItem(CommandSender sender, String categoryName) {
		if (!shopConfig.contains(categoryName)) {
			return ResultCode.CATEGORY_DNE;
		}
		Set<String> keys = shopConfig.getKeys(true);

		String nextItemIndex;
		int counter = 1;
		while (keys.contains(categoryName + ".items.item-" + counter)) {
			nextItemIndex = "item-" + counter;
			ShopItem shopItem = new ShopItem(shopConfig.getString((categoryName + ".items." + nextItemIndex +
					".shopitem")), shopConfig.getItemStack((categoryName + ".items." + nextItemIndex +
					".itemstack")));
			sender.spigot().sendMessage(new TextComponent("§d" + languageConfig.get("item-id") + ": §a" + counter +
					" §d" + languageConfig.get("item-name") + ": §a" + shopItem.getDisplayName()));
			counter++;
		}
		return ResultCode.CODE_OK;
	}

	public ResultCode removeItem(String categoryName, Integer itemID) {
		if (!shopConfig.contains(categoryName)) {
			return ResultCode.CATEGORY_DNE;
		}
		Set<String> keys = shopConfig.getKeys(true);
		int counter = itemID;
		if (!keys.contains(categoryName + ".items.item-" + counter)) {
			return ResultCode.DNE;
		}
		shopConfig.set(categoryName + ".items.item-" + counter, null);
		counter++;
		boolean changed = false;
		while (keys.contains(categoryName + ".items.item-" + counter)) {
			changed = true;
			String itemIndex = "item-" + counter;
			ShopItem shopItem = new ShopItem(shopConfig.getString((categoryName + ".items." + itemIndex +
					".shopitem")), shopConfig.getItemStack((categoryName + ".items." + itemIndex +
					".itemstack")));
			String preItemIndex = "item-" + (counter - 1);
			shopConfig.set((categoryName + ".items." + preItemIndex + ".shopitem"), shopItem.toString());
			shopConfig.set((categoryName + ".items." + preItemIndex + ".itemstack"), shopItem.getItemStack());
			counter++;
		}
		if (changed) {
			counter--;
			shopConfig.set(categoryName + ".items.item-" + counter, null);
		}
		try {
			shopConfig.save(shopConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultCode.CODE_OK;
	}
}


