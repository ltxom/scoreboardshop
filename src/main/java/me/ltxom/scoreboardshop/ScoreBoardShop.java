package me.ltxom.scoreboardshop;

import me.ltxom.scoreboardshop.service.PromptService;
import me.ltxom.scoreboardshop.service.ScoreboardLinkService;
import me.ltxom.scoreboardshop.util.ResultCode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ScoreBoardShop extends JavaPlugin {

	private static final String PREFIX = "[ScoreBoardShop]";
	public static FileConfiguration config;
	public static FileConfiguration languageConfig = new YamlConfiguration();
	public static FileConfiguration scoreboardConfig = new YamlConfiguration();
	private static ScoreboardManager scoreboardManager;
	private static ScoreboardLinkService scoreboardLinkService;
	private static PromptService promptService;

	private void loadConfig() {
		getLogger().info(PREFIX + "Loading configuration...");
		config = getConfig();
		if (!getDataFolder().exists()) getDataFolder().mkdirs();
		saveDefaultConfig();
		if (Objects.requireNonNull(config.get("language")).toString().toLowerCase().equals(
				"chinese")) {
			try {
				languageConfig.load(new File(getDataFolder(), "lang_zh.yml"));
			} catch (IOException | InvalidConfigurationException e) {
				getLogger().info(e.toString());
				getLogger().info(PREFIX + "Error while loading the language configurations");
			}
		} else {
			try {
				languageConfig.load(new File(getDataFolder(), "lang_en.yml"));
			} catch (IOException | InvalidConfigurationException e) {
				getLogger().info(e.toString());
				getLogger().info(PREFIX + "Error while loading the language configurations");
			}
		}
		try {
			scoreboardConfig.load(new File(getDataFolder(), "scoreboard.yml"));
		} catch (IOException | InvalidConfigurationException e) {
			getLogger().info(e.toString());
			getLogger().info(PREFIX + "Error while loading the scoreboard configurations");
		}

		scoreboardLinkService = new ScoreboardLinkService(scoreboardConfig, scoreboardManager);
		promptService = new PromptService(languageConfig);
	}

	@Override
	public void onEnable() {
		loadConfig();

		Bukkit.getPluginCommand("sbs").setExecutor(this);
		scoreboardManager = Bukkit.getScoreboardManager();
		getLogger().info(" ");
		getLogger().info(">>>>>>>>>>>>>>>>>>>>>>>> ScoreBoardShop is Initialized " +
				"<<<<<<<<<<<<<<<<<<<<<<<<");
		getLogger().info(">>>>> Author: ltxom <<<<<");
		getLogger().info(" ");
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
							 String[] args) {
		if ("sbs".equalsIgnoreCase(command.getName())) {
			if (args.length == 0) {
				promptService.getHelp(sender);
				return true;
			}
			if (args[0].equals("link")) {
				if (args[1].equals("list")) {
					if (sender.hasPermission("me.ltxom.sbs.link")) {
						// list all links
						scoreboardLinkService.listAllLinks(sender);
					} else {
						// prompt no permission
						promptService.noPermission(sender);
					}

				} else if (sender.hasPermission("me.ltxom.sbs.link")) {
					try {
						String scoreboardVar = args[2];
						String displayName = args[3];
						if (scoreboardVar != null && displayName != null && !scoreboardVar.isEmpty() && !displayName.isEmpty()) {
							ResultCode resultCode = scoreboardLinkService.linkScoreboard(scoreboardVar,
									displayName);
							switch (resultCode) {
								case CODE_OK:
									promptService.linkedScoreboard(sender);
									break;
								case SCOREBOARD_DNE:
									promptService.scoreboardDNE(sender);
									break;
							}
						} else {
							// command doesn't have valid args
							promptService.commandInvalid(sender);
						}
					} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
						// prompt not valid command
						promptService.commandInvalid(sender);
					}
				} else {
					// prompt no permission
					promptService.noPermission(sender);
				}
			} else if (args[0].equals("unlink")) {
				if (sender.hasPermission("me.ltxom.sbs.unlink")) {
					// unlink
				} else {
					// prompt no permission
				}
			} else if (args[0].equals("category")) {
				if (args[1].equals("create")) {
					if (sender.hasPermission("me.ltxom.sbs.category.create")) {
						// How do you want to handle unexpected inputs?
						// Create a category
						var categoryName = args[2];
						var displayName = args[3];
						var displayItem = args[4];
					} else {
						// prompt no permission
					}
				} else if (args[1].equals("remove")) {
					if (sender.hasPermission("me.ltxom.sbs.category.remove")) {
						// Remove a category
						var categoryName = args[2];
					} else {
						// prompt no permission
					}
				} else if (args[1].equals("list")) {
					if (sender.hasPermission("me.ltxom.sbs.category.remove")) {
						// List all categories
					} else {
						// prompt no permission
					}
				}
			} else if (args[0].equals("item")) {
				if (args[1].equals("create")) {
					if (sender.hasPermission("me.ltxom.sbs.item.create")) {
						// Create an item
						var categoryName = args[2];
						var scoreboardVarName = args[3];
						var price = Integer.parseInt(args[4]);
						var itemType = args[5];
						var itemDesc = args[6];
					} else {
						// prompt no permission
					}
				} else if (args[1].equals("list")) {
					if (sender.hasPermission("me.ltxom.sbs.item.list")) {
						// List an item
						var categoryName = args[2];
					} else {
						// prompt no permission
					}
				} else if (args[1].equals("remove")) {
					if (sender.hasPermission("me.ltxom.sbs.item.remove")) {
						// Remove an item
						var categoryName = args[2];
						var itemID = Integer.parseInt(args[3]);
					} else {
						// prompt no permission
					}
				}
			} else if (args[0].equals("reload")) {
				if (sender.hasPermission("me.ltxom.sbs.reload")) {
					// Reload
				} else {
					// prompt no permission
				}
			} else if (args[0].equals("shop")) {
				if (sender.hasPermission("me.ltxom.sbs.shop")) {
					// Show the shop
				} else {
					// prompt no permission
				}
			} else {
				// Incorrect command || No Permission
			}
		}

		return true;

	}

}

