package me.ltxom.scoreboardshop;

import me.ltxom.scoreboardshop.service.PromptService;
import me.ltxom.scoreboardshop.service.ScoreboardLinkService;
import me.ltxom.scoreboardshop.util.ResultCode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.util.Objects;

public class ScoreBoardShop extends JavaPlugin {

	private static final String PREFIX = "[ScoreBoardShop]";
	public static FileConfiguration config;
	public static FileConfiguration languageConfig;
	public static FileConfiguration scoreboardConfig;
	public static File scoreboardConfigFile;
	private static ScoreboardManager scoreboardManager;
	private static ScoreboardLinkService scoreboardLinkService;
	private static PromptService promptService;

	private void loadConfig() {
		getLogger().info(PREFIX + "Loading configuration...");
		getConfig().options().copyDefaults(true);
		config = getConfig();

		if (!getDataFolder().exists()) getDataFolder().mkdirs();
		saveDefaultConfig();

		File zhFile = new File(getDataFolder(), "lang_zh.yml");
		if (!zhFile.exists()) {
			saveResource("lang_zh.yml", false);
		}
		File enFile = new File(getDataFolder(), "lang_en.yml");
		if (!enFile.exists()) {
			saveResource("lang_en.yml", false);
		}
		if (Objects.requireNonNull(config.get("language")).toString().toLowerCase().equals(
				"chinese")) {
			languageConfig = YamlConfiguration.loadConfiguration(zhFile);
		} else {
			languageConfig = YamlConfiguration.loadConfiguration(enFile);
		}

		scoreboardConfigFile = new File(getDataFolder(), "scoreboard.yml");
		if (!scoreboardConfigFile.exists()) {
			saveResource("scoreboard.yml", false);
		}
		scoreboardConfig = YamlConfiguration.loadConfiguration(scoreboardConfigFile);

		scoreboardManager = Bukkit.getScoreboardManager();

		scoreboardLinkService = new ScoreboardLinkService(scoreboardConfig, scoreboardConfigFile, scoreboardManager);
		promptService = new PromptService(languageConfig);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
							 String[] args) {
		if ("sbs".equalsIgnoreCase(command.getName())) {
			if (args.length == 0) {
				promptService.getHelp(sender);
				return true;
			}
			try {
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
							String scoreboardVar = args[1];
							String displayName = args[2];
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
									case IO_EXCEPTION:
										promptService.exception(sender);
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
							String categoryName = args[2];
							String displayName = args[3];
							String displayItem = args[4];
						} else {
							// prompt no permission
						}
					} else if (args[1].equals("remove")) {
						if (sender.hasPermission("me.ltxom.sbs.category.remove")) {
							// Remove a category
							String categoryName = args[2];
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
							String categoryName = args[2];
							String scoreboardVarName = args[3];
							Integer price = Integer.parseInt(args[4]);
							String itemType = args[5];
							String itemDesc = args[6];
						} else {
							// prompt no permission
						}
					} else if (args[1].equals("list")) {
						if (sender.hasPermission("me.ltxom.sbs.item.list")) {
							// List an item
							String categoryName = args[2];
						} else {
							// prompt no permission
						}
					} else if (args[1].equals("remove")) {
						if (sender.hasPermission("me.ltxom.sbs.item.remove")) {
							// Remove an item
							String categoryName = args[2];
							Integer itemID = Integer.parseInt(args[3]);
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
			} catch (ArrayIndexOutOfBoundsException e) {
				promptService.commandInvalid(sender);
			}
		}

		return true;

	}

	@Override
	public void onDisable() {

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

}

