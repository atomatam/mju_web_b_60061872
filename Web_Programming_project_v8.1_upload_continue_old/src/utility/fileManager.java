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
			System.out.println("첫 번쨰");
			FileDAO.createFile(file);
		} else {
			System.out.println("두 번쨰");
			tempFile.setFilePath(file.getFilePath()+"/"+tempFile.getFilePath());
			FileDAO.updateFile(tempFile);
		}
		System.out.println(FileDAO.getFileFromTitle(file.getFileTitle()).getFilePath());
	}
}
