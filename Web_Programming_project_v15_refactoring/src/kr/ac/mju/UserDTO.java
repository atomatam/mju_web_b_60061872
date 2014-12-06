package kr.ac.mju;

import java.io.Serializable;
import java.sql.Date;
import java.util.Scanner;

public class UserDTO implements Serializable{
	private String user_id;
	private String user_email;
	private String user_nickName;
	private String user_enrollDate;
	private String user_pwd;
	
	public UserDTO() {
	}
	
	public UserDTO(String user_id, String user_email, String user_nickName, String user_enrollDate, String user_pwd) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_nickName = user_nickName;
		this.user_enrollDate = user_enrollDate;
		this.user_pwd = user_pwd;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	public String getUser_enrollDate() {
		return user_enrollDate;
	}

	public void setUser_enrollDate(String user_enrollDate) {
		this.user_enrollDate = user_enrollDate;
	}
	
	
}
