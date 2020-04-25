package me.ltxom.scoreboardshop.util;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public enum ResultCode {

	CODE_OK(200, "Success"),
	ERROR(404, "Error"),
	SCOREBOARD_DNE(405, "Scoreboard doesn't exist"),
	IO_EXCEPTION(406, "IO Exception"),
	DNE(407, "DNE"),
	EXIST(408, "Field already exist")
	;

	private Integer code;
	private String msg;


	ResultCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return this.code;
	}


	public String getMsg() {
		return this.msg;
	}


	@Override
	public String toString() {
		return "code:" + code + ", msg" + msg;
	}

	public static ResultCode saveConfigWithResultCode(FileConfiguration shopConfig, File shopConfigFile) {
		try {
			shopConfig.save(shopConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
			return ResultCode.IO_EXCEPTION;
		}
		return ResultCode.CODE_OK;
	}
}
