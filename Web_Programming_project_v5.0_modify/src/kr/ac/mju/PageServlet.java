package kr.ac.mju;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.*;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static WritingDTO useModify=null;			//정보 수정을 할 경우 쓰는 라이팅디티오 객체를 담아둠. 이것을 쓰지 않으면 콘텐츠가 파싱되서 나옴.writingManager에서	
    /**														//최초 정보를 읽을 때 초기화 함
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String pageLocation = "";
		
		if(op.equals("login")) {
			pageLocation="login.jsp";
		} else if(op.equals("logout")) {
			pageLocation="login.jsp";
			HttpSession session = request.getSession();
			session.invalidate();
		} else if(op.equals("writingSearch")) {
			WritingDTO writing = null;
			writing = writingManager.pasingWritingDTO(request.getParameter("keyword"));
			if(writing==null) {
				request.setAttribute("errorMsg", "검색 결과가 없습니다.");
				pageLocation="error.jsp";
			} else {
				request.setAttribute("writing_title", writing.getWriting_title());
				request.setAttribute("writing_content", writing.getWriting_content());
				request.setAttribute("writing_date", writing.getWriting_date());
				request.setAttribute("writing_writer", writing.getWriting_writer());
				request.setAttribute("writing_history", writing.getWriting_history());
				request.setAttribute("isInitSearch", "false");
				pageLocation="boardMain.jsp";
			}
		} else if(op.equals("searchInfo")) {
			pageLocation="boardMain.jsp";
		} else if(op.equals("newWriting")) {
			pageLocation="newWriting.jsp";
		} 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageLocation);
		dispatcher.forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WritingDTO writing = new WritingDTO();
		String errorMsg = null;
		request.setCharacterEncoding("UTF-8");
		String isLogin = (String) request.getSession().getAttribute("isLogin");
		String pageLocation = "";
		String op = request.getParameter("op");
		String today = writingManager.getCurrentTime();
		if(isLogin!=null) {
			if(op.equals("newWriting")) {
				writing.setWriting_title(request.getParameter("writing_title"));//////////////이게 안읽힘.ㅠㅠ
				writing.setWriting_content(request.getParameter("writing_content"));
				writing.setWriting_date(today);
				writing.setWriting_writer(request.getParameter("writing_writer"));
				writing.setWriting_history(historyManager.writingGetInitHistory(request.getParameter("writing_writer"), today));
				if(WritingDAO.createWriting(writing)) {
					pageLocation = "boardMain.jsp";
					errorMsg = historyManager.setHistoryContent(				//////여기서 히스토리를 만드는 거임.
							request.getParameter("writing_title"), 
							today,request.getParameter("writing_writer"), 
							request.getParameter("writing_content"));
					System.out.println(request.getParameter("writing_title") + request.getParameter("writing_content"));
					if(errorMsg!=null) {
						pageLocation="error.jsp";
					} else {
						pageLocation="boardMain.jsp";
					}
				} else {
					pageLocation = "error.jsp";
					errorMsg = "글 쓰기에 실패했습니다.";
				}
			} else if(op.equals("modify")) {				// 수정 하는 테이블이 있는 페이지로 넘어감.
				request.setAttribute("writing_title", useModify.getWriting_title());
				request.setAttribute("writing_content", useModify.getWriting_content());
				request.setAttribute("writing_date", useModify.getWriting_date());
				request.setAttribute("writing_writer", useModify.getWriting_writer());
				request.setAttribute("writing_history", useModify.getWriting_history());
				pageLocation = "modify.jsp";
			} else if(op.equals("isModify")) {				//수정하는 테이블에서 데이터를 받아와서 디비에 저장하는 단계임.
				useModify.setWriting_date(today);
				System.out.println("여기 오니? : setWritingDate 아래");
				if(writingManager.modifyWriting(useModify, request.getParameter("modify_content"))) {
					System.out.println(request.getParameter("modify_content"));
					WritingDTO writingDto = null;
					System.out.println("여기 오니? : 이프 문 안");
					writingDto = writingManager.pasingWritingDTO(useModify.getWriting_title());
					if(writingDto==null) {
						request.setAttribute("errorMsg", "검색 결과가 없습니다.");
						pageLocation="error.jsp";
					} else {
						request.setAttribute("writing_title", writingDto.getWriting_title());
						request.setAttribute("writing_content", writingDto.getWriting_content());
						request.setAttribute("writing_date", writingDto.getWriting_date());
						request.setAttribute("writing_writer", writingDto.getWriting_writer());
						request.setAttribute("writing_history", writingDto.getWriting_history());
						request.setAttribute("isInitSearch", "false");
						pageLocation="boardMain.jsp";
					}
				} else {
					request.setAttribute("errorMsg", "저장 결과가 없습니다.");
					pageLocation="error.jsp";
				}
			}
		} else {
			try {
				if(op.equals("login")) {
					if(UserIdentity.isExistIdMethod(UserDAO.getAllId(), request.getParameter("user_id"))) {		//파라미터 아이디가 데이터베이스에 있다면
						UserDTO user;
						user = UserDAO.getUserfromId( request.getParameter("user_id"));
						
						if(user.getUser_id().equals(request.getParameter("user_id"))) {
							if(request.getParameter("user_pwd").equals(user.getUser_pwd())) {
								HttpSession session = request.getSession();
								session.setAttribute("isLogin", "true");
								session.setAttribute("user_id", user.getUser_id());
								session.setAttribute("user_nickName", user.getUser_nickName());
								pageLocation = "boardMain.jsp";
							} else {
								pageLocation = "error.jsp";
								errorMsg="비밀번호 또는 아이디가 잘 못 되었습니다. 다시 입력해 주세요.";
							}
						}
					} else {
						pageLocation = "error.jsp";
						errorMsg = "비밀번호 또는 아이디가 잘 못 되었습니다. 다시 입력해 주세요.";
					}
				}
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
				System.out.println(e.toString());
			}
		}
		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageLocation);
		dispatcher.forward(request,  response);
	}
}
