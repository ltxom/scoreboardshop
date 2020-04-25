package me.ltxom.scoreboardshop.util;

public enum ResultCode {

	CODE_OK(200, "Success"),
	ERROR(404, "Error"),
	SCOREBOARD_DNE(405, "Scoreboard doesn't exist")
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
}