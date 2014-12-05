package utility;

import javax.servlet.http.HttpServletRequest;

import kr.ac.mju.WritingDTO;

import org.apache.coyote.Request;

//보낼 request의 속성을 설정해 주는 클래스
public class requestManager {
	
	public static HttpServletRequest setRequestWriting(HttpServletRequest request, WritingDTO writing, String images) {
		request.setAttribute("writing_title", writing.getWriting_title());
		request.setAttribute("writing_content", writing.getWriting_content());
		request.setAttribute("writing_date", writing.getWriting_date());
		request.setAttribute("writing_writer", writing.getWriting_writer());
		request.setAttribute("writing_history", writing.getWriting_history());
		request.setAttribute("images", images);
		return request;
	}
}
