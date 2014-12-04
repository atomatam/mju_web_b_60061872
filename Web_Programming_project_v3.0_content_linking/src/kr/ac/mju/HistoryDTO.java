package kr.ac.mju;

import java.io.Serializable;

public class HistoryDTO implements Serializable{
	private String history_title;
	private String history_content;
	private String history_date;
	
	public HistoryDTO() {
	}

	public String getHistory_title() {
		return history_title;
	}

	public void setHistory_title(String history_title) {
		this.history_title = history_title;
	}

	public String getHistory_content() {
		return history_content;
	}

	public void setHistory_content(String history_content) {
		this.history_content = history_content;
	}

	public String getHistory_date() {
		return history_date;
	}

	public void setHistory_date(String history_date) {
		this.history_date = history_date;
	}
}
