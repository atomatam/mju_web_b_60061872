package utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.naming.NamingException;

import kr.ac.mju.WritingDAO;
import kr.ac.mju.WritingDTO;

public class writingManager {
	
	public static WritingDTO pasingWritingDTO(String Keyword) {
		ArrayList<String> tempTitleList;// 	//title 리스트를 Array에 담아둠.
		String tempContentList[];//	//content를 빈칸 단위로 나눠서 집어 넣음.
		
		WritingDTO writing = null;	//해당 Keyword에 대한 writingDTO
		StringTokenizer tempContent = null;
		String resultContent = null;	//tempContent에서 링크 태그를 넣은 값
		
		writing = WritingDAO.getWritingDTOFromKeyword(Keyword);
		
		if(writing==null) {
			return null;
		}
		
		tempContent = new StringTokenizer(writing.getWriting_content(), " ,", true);
		
		tempContentList = new String[tempContent.countTokens()];
		
		tempContentList = getTempContentList(tempContentList, tempContent);
		tempTitleList = WritingDAO.getAllTitle();
		
		resultContent = transformContent(tempTitleList, tempContentList);
		writing.setWriting_content(resultContent);
		
		return writing;
	}
	
	public static String addTag(String origin) {
		String result = new String("<a href=\"PageServlet?op=writingSearch&keyword="+origin+"\">"+origin+"</a>");
		
		return result;
	}
	
	public static String transformArrayToString(String[] array) {
		StringBuffer result = new StringBuffer();
		for(String content : array) {
			result.append(content);
		}
		
		return result.toString();
	}
	
	public static String transformContent(ArrayList<String> tempTitleList,String[] tempContentList ) {
		String result = null;
		for(String title : tempTitleList) {
			for(int j=0;j<tempContentList.length; j++) {
				if(tempContentList[j].equals(title)) {
					tempContentList[j] = addTag(tempContentList[j]);
				}
			}
		}
		result = transformArrayToString(tempContentList);
		return result;
	}
	
	//tempContent를 " "의 delimiter로 나눠서 tempContentList에서 저장
	public static String[] getTempContentList(String[] tempContentList, StringTokenizer tempContent) {
		
		for(int i=0;i<tempContentList.length; i++) {
			tempContentList[i] = tempContent.nextToken();
		}
		
		return tempContentList;
	}
}
