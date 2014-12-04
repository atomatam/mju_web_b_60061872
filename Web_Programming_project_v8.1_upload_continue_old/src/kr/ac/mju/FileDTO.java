package kr.ac.mju;

import java.io.Serializable;

public class FileDTO implements Serializable {
	private String file_Title;
	private String file_Path;
	private String file_Explain;
	
	public FileDTO() {}
	public FileDTO(String file_Title, String file_Path, String file_Explain) {
		this.file_Title = file_Title;
		this.file_Path = file_Path;
		this.file_Explain = file_Explain;
	}
	public String getFileTitle() {
		return file_Title;
	}
	public void setFileTitle(String file_Title) {
		this.file_Title = file_Title;
	}
	public String getFilePath() {
		return file_Path;
	}
	public void setFilePath(String file_Path) {
		this.file_Path = file_Path;
	}
	public String getFileExplain() {
		return file_Explain;
	}
	public void setFileExplain(String file_Explain) {
		this.file_Explain = file_Explain;
	}
}
