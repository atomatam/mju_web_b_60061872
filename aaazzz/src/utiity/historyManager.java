package utiity;

import kr.ac.mju.*;
import constant.*;

public class historyManager {
	public static String writingGetInitHistory(String date, String content) {
		String result = new String(date);
		return result;
	}
	
	public static String writingGetNotInitHistory() {
		
		return null;
	}
	
	public static void addHistory(WritingDTO writing) {	//히스토리를 만듦. 글을 쓸 때마다 해당 히스토리 타이틀과 라이팅 타이틀이 같은 히스토리 디티오의 튜플에 해당 글의 업데이트 버전 삽입.
		
	}
	
	public static String setHistoryContent(String title, String date, String userName, String tempContent) {	//처음 글을 쓸 때 히스토리디티오에 넣을 콘텐츠를 날짜/사용자/콘텐츠순으로 넣을 준비를 해서 이를 디비에 넣음.
		String content = new String(userName+Constant.historyDelimeter+date+Constant.historyDelimeter+tempContent+Constant.historyDelimeter);
		HistoryDTO history = new HistoryDTO(title, content);
		
		if(HistoryDAO.createHistory(history)) {
			return null;
		}
		
		return String.format("히스토리를 만드는데 실패했습니다.");
	}

}
