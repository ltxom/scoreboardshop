package me.ltxom.scoreboardshop.service;

import me.ltxom.scoreboardshop.util.ResultCode;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardLinkService {
	private FileConfiguration scoreboardConfig;
	private ScoreboardManager scoreboardManager;

	public ScoreboardLinkService(FileConfiguration scoreboardConfig, ScoreboardManager scoreboardManager) {
		this.scoreboardConfig = scoreboardConfig;
		this.scoreboardManager = scoreboardManager;
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
		return ResultCode.CODE_OK;
	}

	public void listAllLinks(CommandSender sender) {

	}

}
