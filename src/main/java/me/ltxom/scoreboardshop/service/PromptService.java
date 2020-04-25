package me.ltxom.scoreboardshop.service;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedList;
import java.util.List;

public class PromptService {
	private FileConfiguration languageConfig;

	public PromptService(FileConfiguration languageConfig) {
		this.languageConfig = languageConfig;
	}

	public void linkedScoreboard(CommandSender sender) {

	}

	public void commandInvalid(CommandSender sender) {

	}

	public void noPermission(CommandSender sender) {

	}


	public void getHelp(CommandSender sender) {
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

	public void scoreboardDNE(CommandSender sender) {
	}
}
