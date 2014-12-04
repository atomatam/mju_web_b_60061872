package kr.ac.mju;

import java.io.Serializable;

public class HistoryDTO implements Serializable{
	private String history_title;
	private String history_content;
	
	public HistoryDTO() {
	}
	
	public HistoryDTO(String history_title, String history_content) {
		this.history_title = history_title;
		this.history_content = history_content;
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
}
