package me.ltxom.scoreboardshop.service;

import me.ltxom.scoreboardshop.util.ResultCode;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
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
}


