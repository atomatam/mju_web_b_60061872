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

import org.apache.tomcat.jni.User;

import utiity.*;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String preTagContent=null;
    /**
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
		} else if(op.equals("searchInfo")) {
			pageLocation="boardMain.jsp";
		} else if(op.equals("newWriting")) {
			pageLocation="newWriting.jsp";
		} else if(op.equals("memberEnroll")) {
//			String userid = request.getParameter("user_id");
			UserDTO user= new UserDTO();
			boolean check = UserDAO.insertMember(user);
			System.out.println("Aaaaaaaaaaaaaa "+check);
			if(!check) {
			
				pageLocation="error.jsp";		
			} else{
			
			}
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
		String beforeContent = null; 
		if(isLogin!=null) {
			if(op.equals("newWriting")) {
				System.out.println("newWiriting");
				writing.setWriting_title(request.getParameter("writing_title"));
				writing.setWriting_content(request.getParameter("writing_content"));
				writing.setWriting_date(today);
				writing.setWriting_writer(request.getParameter("writing_writer"));
				writing.setWriting_history(historyManager.writingGetInitHistory(today, request.getParameter("writing_content")));
				if(WritingDAO.createWriting(writing)) {
					pageLocation = "boardMain.jsp";
					errorMsg = historyManager.setHistoryContent(
							request.getParameter("writing_title"), 
							today,request.getParameter("writing_writer"), 
							request.getParameter("writing_content"));
					if(errorMsg!=null) {
						pageLocation="error.jsp";
					} else {
						pageLocation="boardMain.jsp";
					}
				} else {
					pageLocation = "error.jsp";
					errorMsg = "글 쓰기에 실패했습니다.";
				}
			} else if(op.equals("writingSearch") || op.equals("modify")) {
				if(op.equals("writingSearch")) {
					writing = writingManager.pasingWritingDTO(request.getParameter("keyword"));
					request.setAttribute("modifyMode", "false");
				} else {
					writing = writingManager.getWritingDTO(request.getParameter("keyword"));
					request.setAttribute("modifyMode", "true");
				}
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
			}
		}
		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageLocation);
		dispatcher.forward(request,  response);
		
		boolean ret=false;
		String actionUrl;
		String msg;
		User user = new User();
		
		request.setCharacterEncoding("utf-8");
		
		String user_id=request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		String user_nickName = request.getParameter("user_nickName");
		String user_email = request.getParameter("user_email");
		
		List<String> errorMsgs=new ArrayList<String>();
		
		if(user_pwd==null){
			errorMsgs.add("비밀번호를 입력해주세요.");
		}
		user.setPwd(pwd);
			
		}
	}
}

