package kr.ac.mju;

import java.io.*;
import java.util.*;

import kr.ac.mju.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





import utility.*;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static WritingDTO useModify=null;			//정보 수정을 할 경우 쓰는 라이팅디티오 객체를 담아둠. 이것을 쓰지 않으면 콘텐츠가 파싱되서 나옴.writingManager에서	
    /**														//최초 정보를 읽을 때 초기화 함
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String pageLocation = "";
		if(op.equals("logout")) {
			pageLocation="login.jsp";
			HttpSession session = request.getSession();
			session.invalidate();
			response.setHeader("Cache-Control","no-store");   
			response.setHeader("Pragma","no-cache");   
			response.setDateHeader("Expires",0);   
			if (request.getProtocol().equals("HTTP/1.1")) 
			        response.setHeader("Cache-Control", "no-cache"); 
		} else if(op.equals("writingSearch")) {
			WritingDTO writing = null;
			writing = writingManager.pasingWritingDTO(request.getParameter("keyword"));
			if(writing==null) {
				request.setAttribute("errorMsg", "검색 결과가 없습니다.");
				pageLocation="error.jsp";
			} else {
				String images = fileManager.getParsedFile(writing.getWriting_title());
				request = requestManager.setRequestWriting(request, writing, images);				//image에 path와 explain이 getParseFile에서 합쳐짐
				request.setAttribute("isInitSearch", "false");
				pageLocation = "manager.jsp";
			}
		} else if(op.equals("showHistory")) {
			String date = request.getParameter("date");
			String historyInfor[] = historyManager.getHistoryInforFromDate(date);
			request.setAttribute("title", useModify.getWriting_title());
			request.setAttribute("date", historyInfor[0]);
			request.setAttribute("writer", historyInfor[1]);
			request.setAttribute("content", historyInfor[2]);
			pageLocation = "historyPage.jsp";
		} else if(op.equals("userSearch")) {
			String allUsersInform = userManager.managingAllUser();
			String banedUsers = userManager.getBanList();
			if(allUsersInform == null) {
				pageLocation = "error.jsp";
				request.setAttribute("errorMsg", "유저가 없습니다");
			} else {
				request.setAttribute("userList", allUsersInform);
				request.setAttribute("banedUserList",banedUsers );
				pageLocation = "userList.jsp";
			}
		} else if(op.equals("ban")){
			String ban_id = request.getParameter("ban_id");
			BanDTO ban = new BanDTO(ban_id);
			userManager.managingBanList(ban);
			pageLocation="ManagerServlet?op=userSearch";
		} else if(op.equals("releaseBan")) {
			String ban_Id = request.getParameter("ban_Id");
			userManager.freeBan(ban_Id);
			
			String allUsersInform = userManager.managingAllUser();
			String banedUsers = userManager.getBanList();
			if(allUsersInform == null) {
				pageLocation = "error.jsp";
				request.setAttribute("errorMsg", "유저가 없습니다");
			} else {
				request.setAttribute("userList", allUsersInform);
				request.setAttribute("banedUserList",banedUsers );
				pageLocation = "userList.jsp";
			}
		} else if(op.equals("deleteContent")) {
		
			WritingDTO writing = null;
			writing = WritingDAO.getWritingDTOFromKeyword(request.getParameter("keyword"));
			String errorMsg=managingManager.deleteContent(writing);						//위에서 키워드를 이용해 가장 최신의 라이팅을 지우는 함수를 호출함.
			if(errorMsg!=null) {
				pageLocation = "managerError.jsp";
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("keyword", writing.getWriting_title());
			} else {
				WritingDTO writingDto = null;
				writingDto = writingManager.pasingWritingDTO(request.getParameter("keyword"));
				if(writingDto==null) {
					request.setAttribute("errorMsg", "검색 결과가 없습니다.");
					pageLocation="error.jsp";
				} else {
					String images = fileManager.getParsedFile(writingDto.getWriting_title());
					request = requestManager.setRequestWriting(request, writingDto, images);				//image에 path와 explain이 getParseFile에서 합쳐짐
					request.setAttribute("isInitSearch", "false");
					
					pageLocation = "manager.jsp";
				}
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageLocation);
		dispatcher.forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMsg = null;
		request.setCharacterEncoding("UTF-8");
		String isLogin = (String) request.getSession().getAttribute("isLogin");
		String pageLocation = "";
		String op = request.getParameter("op");
		pageLocation = "manager.jsp";
		
		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageLocation);
		dispatcher.forward(request,  response);
	}
}