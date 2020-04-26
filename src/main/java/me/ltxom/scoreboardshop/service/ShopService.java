package me.ltxom.scoreboardshop.service;

import me.ltxom.scoreboardshop.entity.ShopItem;
import me.ltxom.scoreboardshop.util.ResultCode;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
								 String itemLore, String itemType, String material,
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
		if (shopConfig.get(categoryName + ".items") == null) {
			shopConfig.set((categoryName + ".items"), new ArrayList<ShopItem>());
		}
		ShopItem shopItem = new ShopItem();
		shopItem.setCategoryName(categoryName);
		shopItem.setDisplayName(itemDisplayName);
		shopItem.setScoreboardVarName(scoreboardVarName);
		shopItem.setPrice(price);
		shopItem.setLore(itemLore);
		ArrayList list = (ArrayList) shopConfig.get(categoryName + ".items");
		if (itemType.toLowerCase().equals("hand")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack itemStack = player.getInventory().getItemInMainHand();
				shopItem.setItemStack(itemStack);
			} else {
				return ResultCode.ERROR;
			}
		} else if (itemType.toLowerCase().equals("command")) {
			shopItem.setItemCommand(itemCommand);
			shopItem.setMaterial(material);
		} else {
			return ResultCode.ILLGEAL_ARG;
		}
		list.add(shopItem);
		shopConfig.set((categoryName + ".items"), list);
		try {
			shopConfig.save(shopConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultCode.CODE_OK;
	}
}


