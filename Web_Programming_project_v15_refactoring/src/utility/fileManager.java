package utility;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

import kr.ac.mju.FileDAO;
import kr.ac.mju.FileDTO;

public class fileManager {

	public static void createFile(FileDTO file) {			///파일을 올리면  이것이 호출 되어저장이 됨.
		FileDTO tempFile = null;
		
		tempFile = FileDAO.getFileFromTitle(file.getFileTitle());
		if(tempFile==null) {
			file.setFileExplain("&"+file.getFileExplain());
			FileDAO.createFile(file);
		} else {
			file.setFileExplain("&"+file.getFileExplain());
			tempFile.setFilePath(file.getFilePath()+"/"+tempFile.getFilePath());
			tempFile.setFileExplain(file.getFileExplain()+"/"+tempFile.getFileExplain());
			FileDAO.updateFile(tempFile);
		}
	}
	
	public static String getParsedFile(String title) { //writingdto의 title을 이용하여 화면에 뿌려질 파일을 태그를 포함해서 줌.
		FileDTO file = FileDAO.getFileFromTitle(title);
		if(file!=null) {
		StringTokenizer tempImages =  new StringTokenizer(file.getFilePath(), "/" );
		StringTokenizer tempExplain = new StringTokenizer(file.getFileExplain(), "/");
		String images[] = new String[tempImages.countTokens()];
		String explain[] = new String[tempExplain.countTokens()];
		String result = null;
			for(int i=0; i<images.length; i++) {
				images[i] = tempImages.nextToken();
				explain[i] = tempExplain.nextToken();
			}
			images = getParsingImage(images);
			result = sumTwoArray(images, explain);
			return result;
		}
		return null;
	}
	
	public static String[] getParsingImage(String[] images) {
		for(int i=0; i<images.length; i++) {
			images[i] = "<img src=\""+images[i]+"\">";
			System.out.println(images[i]);
		}
		return images;
	}
	
	public static String sumTwoArray(String[] array1, String[] array2) {
		StringBuffer str = new StringBuffer();
		for(int i=0; i<array1.length; i++) {
			str.append(array1[i]+"\r\n");
			str.append(array2[i]);
		}
		
		return str.toString();
	}
}
