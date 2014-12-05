package kr.ac.mju;

import java.io.Serializable;

public class BanDTO implements Serializable {
	private String ban_id;
	private String ban_date;
	public BanDTO () {
	}
	public BanDTO(String ban_id) {
		this.ban_id = ban_id;
	}
	public BanDTO(String ban_id, String ban_date) {
		this.ban_id = ban_id;
		this.ban_date = ban_date;
	}
	public String getBan_id() {
		return ban_id;
	}
	public void setBan_id(String ban_id) {
		this.ban_id = ban_id;
	}
	public String getBan_date() {
		return ban_date;
	}
	public void setBan_date(String ban_date) {
		this.ban_date = ban_date;
	}
}
