package me.ltxom.scoreboardshop;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoardShop extends JavaPlugin {

	private static final String PREFIX = "[ScoreBoardShop}";
	public static FileConfiguration config;
	private static ScoreboardManager scoreboardManager;

	private void loadConfig() {
		getLogger().info(PREFIX + "Loading configuration...");
		config = getConfig();
		if (!getDataFolder().exists()) getDataFolder().mkdirs();
		saveDefaultConfig();
	}

	@Override
	public void onEnable() {
		loadConfig();

		Bukkit.getPluginCommand("sbs").setExecutor(this);
		scoreboardManager = Bukkit.getScoreboardManager();
		getLogger().info(" ");
		getLogger().info(">>>>>>>>>>>>>>>>>>>>>>>> ScoreBoardShop is Initialized <<<<<<<<<<<<<<<<<<<<<<<<");
		getLogger().info(">>>>> Author: ltxom <<<<<");
		getLogger().info(" ");
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ("sbs".equalsIgnoreCase(command.getName())) {
			if (args.length == 0) {
				getHelp(sender);
				return true;
			}
			if (args[0].equals("link")) {
				if (args[1].equals("list")) {
					if (sender.hasPermission("me.ltxom.sbs.link")) {
						// list all links
					} else {
						// prompt no permission
					}

				} else if (sender.hasPermission("me.ltxom.sbs.link")) {
					String scoreboardVar = args[2];
					String displayName = args[3];
					if (scoreboardVar != null && displayName != null && !scoreboardVar.isEmpty() && !displayName.isEmpty()) {
						// check if the scoreborad variable exist
						try {
							Objective objective = scoreboardManager.getMainScoreboard().getObjective(scoreboardVar);

						} catch (IllegalArgumentException e) {
							// the scoreboard variable is not exist
						}
					} else {
						// command doesn't have valid args
					}
				} else {
					// prompt no permission
					noPermission();
				}
			} else if (args[0].equals("unlink")){
				if (sender.hasPermission("me.ltxom.sbs.unlink")) {
					// unlink
				} else {
					// prompt no permission
				}
			} else if (args[0].equals("category")){
				if (args[1].equals("create")) {
					if (sender.hasPermission("me.ltxom.sbs.category.create")) {
						// Create a category
					} else {
						// prompt no permission
					}
				}
				else if(args[1].equals("remove")){
					if (sender.hasPermission("me.ltxom.sbs.category.remove")) {
						// Remove a category
					} else {
						// prompt no permission
					}
				}
			} else if (args[0].equals("item")){
				if (args[1].equals("create")){
					if (sender.hasPermission("me.ltxom.sbs.item.create")) {
						// Create an item
					} else {
						// prompt no permission
					}
				} else if (args[1].equals("list")){
					if (sender.hasPermission("me.ltxom.sbs.item.list")) {
						// List an item
					} else {
						// prompt no permission
					}
				} else if (args[1].equals("remove")){
					if (sender.hasPermission("me.ltxom.sbs.item.remove")) {
						// Remove an item
					} else {
						// prompt no permission
					}
				}
			}
		}

		return true;
	}


	private void hasPermission(){

	}

	private void noPermission(){

	}

	private void getHelp(CommandSender sender) {
		// TODO 多语言支持、优化颜色、
		TextComponent help = new TextComponent("简介\n" +
				"\n" +
				"与Minecraft内置Scoreboard积分板结合的商店插件。\n" +
				"\n" +
				"TODO (括号内为权限节点)\n" +
				"\n" +
				"管理员\n" +
				"\n" +
				"scoreboard：\n" +
				"\n" +
				"/scoreboardshop:sbs link [scoreboard variable name] [display name]：使Minecraft scoreboard与scoreboard" +
				" " +
				"shop变量连接，并赋予显示名称(me.ltxom.sbs.link)\n" +
				"/scoreboardshop:sbs unlink [scoreboard variable name]：使Minecraft scoreboard与scoreboard shop变量取消连接" +
				"(me" +
				".ltxom.sbs.link.unlink)\n" +
				"/scoreboardshop:sbs link list：显示已建立的连接(me.ltxom.sbs.link.list)\n" +
				"商店：\n" +
				"\n" +
				"/scoreboardshop:sbs category create [category name] [display name] [display " +
				"item]：创建商店中的类别，通过指定的显示名称与显示物品(me.ltxom.sbs.category.create)\n" +
				"/scoreboardshop:sbs category remove [category name]：移除商店中的类别，类别下的物品也会被移除(me.ltxom.sbs.category" +
				".remove)\n" +
				"/scoreboardshop:sbs category list：显示所有类别，包括其类别名称、显示名称\n" +
				"/scoreboardshop:sbs item create [category name] [scoreboard variable name] [price] [item type] " +
				"[item" +
				" " +
				"desc]：创建商品，指定类别、scoreboard变量、价格、物品类型、以及附加属性(me.ltxom.sbs.item.create)\n" +
				"物品种类[item type]以及其附加属性[item desc]\n" +
				"[HAND] [NUMBER]：从手中的物品创建并指定数量\n" +
				"[Command]：执行任意指令，使用%p%代表玩家名\n" +
				"/scoreboardshop:sbs item list [category name]：显示指定种类下所有的物品，包括物品id、scoreboard变量、价格、物品种类、以及附加属性(me" +
				".ltxom.sbs.item.list)\n" +
				"/scoreboardshop:sbs item remove [category name] [item id]：移除指定种类下的物品(me.ltxom.sbs.item.remove)\n" +
				"管理：\n" +
				"\n" +
				"/scoreboardshop:sbs reload：重载插件(me.ltxom.sbs.reload)\n" +
				"用户\n" +
				"\n" +
				"/scoreboardshop:sbs shop：显示scoreboardshop商店(me.ltxom.sbs.shop)");
		sender.spigot().sendMessage(help);
	}
}
