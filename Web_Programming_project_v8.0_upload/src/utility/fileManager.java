package utility;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import kr.ac.mju.FileDAO;
import kr.ac.mju.FileDTO;

public class fileManager {

	public static void createFile(FileDTO file) {
		FileDTO tempFile = null;
		
		tempFile = FileDAO.getFileFromTitle(file.getFileTitle());
		System.out.println("이프 전");
		if(tempFile==null) {
			System.out.println("이프 안");
			System.out.println(file.getFilePath());
			FileDAO.createFile(file);
		} else {
			System.out.println("엘즈 안");
			tempFile.setFilePath(tempFile.getFilePath()+"/"+file.getFilePath());
			FileDAO.createFile(tempFile);
		}
	}
}
