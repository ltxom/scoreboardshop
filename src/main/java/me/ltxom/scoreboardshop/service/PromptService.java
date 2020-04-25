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
		TextComponent textComponent = new TextComponent("§a" + languageConfig.get(
				"linked-scoreboard"));
		sender.spigot().sendMessage(textComponent);
	}

	public void commandInvalid(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§c" + languageConfig.get(
				"command-invalid"));
		sender.spigot().sendMessage(textComponent);
	}

	public void noPermission(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§c" + languageConfig.get(
				"no-permission"));
		sender.spigot().sendMessage(textComponent);
	}


	public void getHelp(CommandSender sender) {
		List<TextComponent> toSendList = new LinkedList<>();
		if (sender.hasPermission("me.ltxom.sbs.link")) {
			TextComponent textComponent = new TextComponent("§b/sbs link  \n§a" + languageConfig.get(
					"link-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs link"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"link-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.unlink")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs unlink  \n§a" + languageConfig.get(
							"unlink-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs unlink"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"unlink-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.link.list")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs link list  \n§a" + languageConfig.get(
							"link-list-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs link list"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"link-list-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.category.create")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs category create  \n§a" + languageConfig.get(
							"category-create-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs category create"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"category-create-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.category.remove")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs category remove  \n§a" + languageConfig.get(
							"category-remove-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs category remove"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"category-remove-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.category.list")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs category list  \n§a" + languageConfig.get(
							"category-list-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs category list"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"category-list-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.category.item.create")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs item create  \n§a" + languageConfig.get(
							"item-create-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs item create"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"item-create-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);

		}
		if (sender.hasPermission("me.ltxom.sbs.item.list")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs item list  \n§a" + languageConfig.get(
							"item-list-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs item list"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"item-list-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.item.remove")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs item remove  \n§a" + languageConfig.get(
							"item-remove-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs item remove"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"item-remove-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.reload")) {
			TextComponent textComponent =
					new TextComponent("§b/sbs reload  \n§a" + languageConfig.get(
							"reload-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs reload"));
			textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder((String) languageConfig.get(
							"reload-help")).color(ChatColor.BLUE).create()));
			toSendList.add(textComponent);
		}
		if (sender.hasPermission("me.ltxom.sbs.shop")) {
			TextComponent textComponent = new TextComponent("§b/sbs shop  \n§a" + languageConfig.get(
					"shop-help"));
			
			textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					"§b/sbs shop"));
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
		TextComponent textComponent = new TextComponent("§c" + languageConfig.get(
				"scoreboard-DNE"));
		sender.spigot().sendMessage(textComponent);
	}

	public void exception(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§c" + languageConfig.get(
				"exception"));
		sender.spigot().sendMessage(textComponent);
	}

	public void unlinked(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§a" + languageConfig.get(
				"unlinked"));
		sender.spigot().sendMessage(textComponent);
	}

	public void createdCategory(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§a" + languageConfig.get(
				"category-created"));
		sender.spigot().sendMessage(textComponent);
	}

	public void fieldExist(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§c" + languageConfig.get(
				"field-exist"));
		sender.spigot().sendMessage(textComponent);
	}

	public void removedCategory(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§a" + languageConfig.get(
				"category-removed"));
		sender.spigot().sendMessage(textComponent);
	}

	public void categoryDNE(CommandSender sender) {
		TextComponent textComponent = new TextComponent("§c" + languageConfig.get(
				"category-dne"));
		sender.spigot().sendMessage(textComponent);
	}
}
