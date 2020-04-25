package me.ltxom.scoreboardshop;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ScoreBoardShop extends JavaPlugin {

    private static final String PREFIX = "[ScoreBoardShop]";
    public static FileConfiguration config;
    public static FileConfiguration languageConfig = new YamlConfiguration();
    ;
    private static ScoreboardManager scoreboardManager;

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
                getLogger().info(PREFIX + "Error while loading the configurations");
            }
        } else {
            try {
                languageConfig.load(new File(getDataFolder(), "lang_en.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                getLogger().info(e.toString());
                getLogger().info(PREFIX + "Error while loading the configurations");
            }
        }
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
                            Objective objective =
                                    scoreboardManager.getMainScoreboard().getObjective(scoreboardVar);

                        } catch (IllegalArgumentException e) {
                            // the scoreboard variable is not exist
                        }
                    } else {
                        // command doesn't have valid args
                    }
                } else {
                    // prompt no permission
                }
            }
        }

        return true;
    }

    private void getHelp(CommandSender sender) {
        List<TextComponent> toSendList = new LinkedList<>();
        if (sender.hasPermission("me.ltxom.sbs.link")) {
            TextComponent textComponent = new TextComponent("/sbs link  " + languageConfig.get(
                    "link-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs link"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "link-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.unlink")) {
            TextComponent textComponent =
                    new TextComponent("/sbs unlink  " + languageConfig.get(
                    "unlink-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs unlink"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "unlink-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.link.list")) {
            TextComponent textComponent =
                    new TextComponent("/sbs link list  " + languageConfig.get(
                    "link-list-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs link list"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "link-list-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.category.create")) {
            TextComponent textComponent =
                    new TextComponent("/sbs category create  " + languageConfig.get(
                    "category-create-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs category create"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "category-create-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.category.remove")) {
            TextComponent textComponent =
                    new TextComponent("/sbs category remove  " + languageConfig.get(
                    "category-remove-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs category remove"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "category-remove-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.category.list")) {
            TextComponent textComponent =
                    new TextComponent("/sbs category list  " + languageConfig.get(
                    "category-list-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs category list"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "category-list-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.category.item.create")) {
            TextComponent textComponent =
                    new TextComponent("/sbs item create  " + languageConfig.get(
                    "item-create-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs item create"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "item-create-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);

        }
        if (sender.hasPermission("me.ltxom.sbs.item.list")) {
            TextComponent textComponent =
                    new TextComponent("/sbs item list  " + languageConfig.get(
                    "item-list-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs item list"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "item-list-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.item.remove")) {
            TextComponent textComponent =
                    new TextComponent("/sbs item remove  " + languageConfig.get(
                    "item-remove-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs item remove"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "item-remove-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.reload")) {
            TextComponent textComponent =
                    new TextComponent("/sbs reload  " + languageConfig.get(
                    "reload-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs reload"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "reload-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        if (sender.hasPermission("me.ltxom.sbs.shop")) {
            TextComponent textComponent = new TextComponent("/sbs shop  " + languageConfig.get(
                    "shop-help"));
            textComponent.setColor(ChatColor.GRAY);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                    "/sbs shop"));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder((String) languageConfig.get(
                            "shop-help")).color(ChatColor.BLUE).create()));
            toSendList.add(textComponent);
        }
        for (TextComponent textComponent : toSendList) {
            sender.spigot().sendMessage(textComponent);
        }

    }
}
