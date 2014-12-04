package kr.ac.mju;

import java.io.Serializable;

public class WritingDTO implements Serializable{
	private String writing_title;
	private String writing_content;
	private String writing_date;
	private String writing_writer;
	private String writing_history;
	
	public WritingDTO() {	
	}

	public String getWriting_title() {
		return writing_title;
	}

	public void setWriting_title(String writing_title) {
		this.writing_title = writing_title;
	}

	public String getWriting_content() {
		return writing_content;
	}

	public void setWriting_content(String writing_content) {
		this.writing_content = writing_content;
	}

	public String getWriting_date() {
		return writing_date;
	}

	public void setWriting_date(String writing_date) {
		this.writing_date = writing_date;
	}

	public String getWriting_writer() {
		return writing_writer;
	}

	public void setWriting_writer(String writing_writer) {
		this.writing_writer = writing_writer;
	}

	public String getWriting_history() {
		return writing_history;
	}

	public void setWriting_history(String writing_history) {
		this.writing_history = writing_history;
	}
	
}
