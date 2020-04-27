package me.ltxom.scoreboardshop.service;

import me.ltxom.scoreboardshop.util.ResultCode;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.util.Set;

public class ScoreboardLinkService {
	private FileConfiguration scoreboardConfig;
	private ScoreboardManager scoreboardManager;
	private File scoreboardConfigFile;
	private FileConfiguration languageConfig;

	public ScoreboardLinkService(FileConfiguration scoreboardConfig, File scoreboardConfigFile,
								 ScoreboardManager scoreboardManager, FileConfiguration languageConfig) {
		this.scoreboardConfig = scoreboardConfig;
		this.scoreboardManager = scoreboardManager;
		this.scoreboardConfigFile = scoreboardConfigFile;
		this.languageConfig = languageConfig;
	}

	public boolean scoreboardExist(String scoreboardVar) {
		Objective objective =
				scoreboardManager.getMainScoreboard().getObjective(scoreboardVar);
		return objective != null;
	}

	public ResultCode linkScoreboard(String scoreboardVar, String displayName) {
		if (!scoreboardExist(scoreboardVar)) {
			return ResultCode.SCOREBOARD_DNE;
		}
		scoreboardConfig.set(scoreboardVar, displayName);
		return ResultCode.saveConfigWithResultCode(scoreboardConfig, scoreboardConfigFile);
	}

	public void listAllLinks(CommandSender sender) {
		Set<String> keys = scoreboardConfig.getKeys(false);
		if (keys.isEmpty()) {
			sender.sendMessage("§c" + languageConfig.get("no-link").toString());
			return;
		}
		for (String key : keys) {
			String value = scoreboardConfig.get(key).toString();
			TextComponent textComponent =
					new TextComponent("§a" + languageConfig.get("scoreboard-var") + ": §b" + key + "    §a" + languageConfig.get("display-name") + ": §r" + value);
			sender.spigot().sendMessage(textComponent);
		}
	}

	public ResultCode unlink(String scoreboardVar) {
		if (!scoreboardConfig.contains(scoreboardVar)) {
			return ResultCode.DNE;
		}
		scoreboardConfig.set(scoreboardVar, null);
		return ResultCode.CODE_OK;
	}
}
