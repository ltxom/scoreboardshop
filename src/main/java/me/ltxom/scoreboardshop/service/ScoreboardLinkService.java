package me.ltxom.scoreboardshop.service;

import me.ltxom.scoreboardshop.util.ResultCode;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ScoreboardLinkService {
	private FileConfiguration scoreboardConfig;
	private ScoreboardManager scoreboardManager;
	private File scoreboardConfigFile;

	public ScoreboardLinkService(FileConfiguration scoreboardConfig, File scoreboardConfigFile,
								 ScoreboardManager scoreboardManager) {
		this.scoreboardConfig = scoreboardConfig;
		this.scoreboardManager = scoreboardManager;
		this.scoreboardConfigFile = scoreboardConfigFile;
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
		try {
			scoreboardConfig.save(scoreboardConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
			return ResultCode.IO_EXCEPTION;
		}
		return ResultCode.CODE_OK;
	}

	public void listAllLinks(CommandSender sender) {
		Set<String> keys = scoreboardConfig.getKeys(true);
		for (String key : keys) {
			String value = scoreboardConfig.get(key).toString();
			TextComponent textComponent =
					new TextComponent("§aScoreboard variable: §b" + key + "\t§aDisplay name: §r" + value);
			sender.spigot().sendMessage(textComponent);
		}
	}

}
