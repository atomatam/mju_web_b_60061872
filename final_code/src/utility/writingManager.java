package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;



import constant.Constant;
import kr.ac.mju.*;

public class writingManager {
	
	public static boolean modifyWriting(WritingDTO writing, String content) {
		String tempHistory = writing.getWriting_history();
		writing.setWriting_content(content);
		writing.setWriting_history("<a href=\"PageServlet?op=showHistory&date=" +
									writing.getWriting_date()+"\">"+writing.getWriting_writer()+" "+
				writing.getWriting_date()+"</a>"+Constant.historyDelimeter+"\n"+tempHistory);
		if(!WritingDAO.removeWriting(writing.getWriting_title())) return false;
		if(!WritingDAO.createWriting(writing)) return false;
		return true;
	}
	
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
		PageServlet.useModify = new WritingDTO(writing.getWriting_title(),
									writing.getWriting_content(),
									writing.getWriting_date(),
									writing.getWriting_writer(),
									writing.getWriting_history());
		
		tempContent = new StringTokenizer(writing.getWriting_content(), " ,\n\r", true);
		
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
	
	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String today = dateFormat.format(calendar.getTime());
		
		return today;
	}

	public static boolean isExistWriting(String title) {
		if(WritingDAO.getWritingDTOFromKeyword(title)==null)
			return false;
		return true;
	}
}
