package utility;

import java.util.StringTokenizer;

import kr.ac.mju.HistoryDAO;
import kr.ac.mju.HistoryDTO;
import kr.ac.mju.WritingDAO;
import kr.ac.mju.WritingDTO;
import utility.*;

public class managingManager {

	public static String deleteContent(WritingDTO writing) {	//writingdto의 content를 바꾸고, historydto를 바꾸고, 
		//////history바꾸기
		HistoryDTO tempHistory = HistoryDAO.getHistoryFromTitle(writing.getWriting_title());
		StringTokenizer tempHistoryContent = new StringTokenizer(tempHistory.getHistory_content() , "/");	//콘텐츠를 담은 스트링 토크나이져
		String[] historyContents = new String[tempHistoryContent.countTokens()];		//tempcontent를 / 단위로 잘라서 집어 넣을 배열
	
		if(historyContents.length <= 3)
			return String.format("처음 글 입니다.");
		
		StringBuffer historyRealContents = new StringBuffer();					//tempcontent의 앞부분 세개를 잘라서 넣을 공간
		
		historyContents = writingManager.getTempContentList(historyContents, tempHistoryContent);
		
		for(int i=3; i<historyContents.length; i++) {
			historyRealContents.append(historyContents[i]+"/");
		}
		tempHistory.setHistory_content(historyRealContents.toString());
		HistoryDAO.updateHistory(tempHistory);
		//////writing 바꾸기
			
		WritingDTO tempWriting = WritingDAO.getWritingDTOFromKeyword(writing.getWriting_title());
		StringTokenizer tempContent = new StringTokenizer(tempWriting.getWriting_history(), "/");	//콘텐츠를 담은 스트링 토크나이져
		String[] contents = new String[tempContent.countTokens()];		//tempcontent를 / 단위로 잘라서 집어 넣을 배열
		StringBuffer realContents = new StringBuffer();					//tempcontent의 앞부분 세개를 잘라서 넣을 공간

		WritingDAO.removeWriting(writing.getWriting_title());
		
		contents = writingManager.getTempContentList(contents, tempContent);
		
		for(int i=2; i<contents.length; i++) {
			realContents.append(contents[i]+"/");
		}
		tempWriting.setWriting_content(historyContents[5]);
		tempWriting.setWriting_history(realContents.toString());
		WritingDAO.createWriting(tempWriting);
					
		return null;
	}
}
