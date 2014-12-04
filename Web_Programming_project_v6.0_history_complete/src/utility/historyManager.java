package utility;

import kr.ac.mju.HistoryDAO;
import kr.ac.mju.HistoryDTO;
import kr.ac.mju.WritingDTO;
import constant.Constant;

public class historyManager {
	public static String writingGetInitHistory(String userName, String date) {
		String result = new String(userName+" "+date);
		return result;
	}
	
	public static String setHistoryContent(String title, String date, String userName, String tempContent) {	//처음 글을 쓸 때 히스토리디티오에 넣을 콘텐츠를 날짜/사용자/콘텐츠순으로 넣을 준비를 해서 이를 디비에 넣음.
		String content = new String(userName+" "+date+Constant.historyDelimeter+tempContent);
		HistoryDTO history = new HistoryDTO(title, content);
		
		if(HistoryDAO.createHistory(history)) {
			return null;
		}
		return String.format("히스토리를 만드는데 실패했습니다.");
	}
	/////////////////////<-- historydto tables-->////////////////title, content(date/writer/content)
	public static String createHistory(WritingDTO writing) {
		String historyTitle = writing.getWriting_title();
		String historyContent = new String(writing.getWriting_date()+Constant.historyDelimeter+
				writing.getWriting_writer()+Constant.historyDelimeter+writing.getWriting_content());
		
		HistoryDTO history = new HistoryDTO(historyTitle, historyContent);
		if(HistoryDAO.createHistory(history)) {
			return null;
		}
		return String.format("히스토리를 만드는데 실패했습니다.");
	}
	
	public static String modifyHistory(WritingDTO writing) {
		HistoryDTO preHistory = HistoryDAO.getHistoryFromTitle(writing.getWriting_title());
		preHistory.setHistory_content(new String(preHistory.getHistory_content()+Constant.historyDelimeter+				//히스토리의 콘텐츠를 갱신함.
								writing.getWriting_date()+Constant.historyDelimeter+writing.getWriting_writer()+
								Constant.historyDelimeter+writing.getWriting_content()));
		
		if(HistoryDAO.updateHistory(preHistory)) {
			return null;
		}
		
		return String.format("히스토리를 갱신하는데 실패했습니다.");
	}
}
