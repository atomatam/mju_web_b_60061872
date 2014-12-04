package kr.ac.mju;

import java.io.IOException;
import java.sql.SQLException;
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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String pageLocation = "";
		
		if(op.equals("login")) {
			pageLocation="login.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageLocation);
		dispatcher.forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String isLogin = (String) request.getSession().getAttribute("isLogin");
		
		
		if(isLogin!=null) {
			
		} else {
			try {
				if(UserIdentity.isExistIdMethod(UserDAO.getAllId(), request.getParameter("user_id"))) {				//파라미터 아이디가 데이터베이스에 있다면
					UserDTO user;
					user = UserDAO.getUserfromId( request.getParameter("user_id"));
					if(request.getParameter("user_pwd").equals(user.getUser_pwd())) {
						HttpSession session = request.getSession();
						session.setAttribute("isLogin", "true");
						session.setAttribute("user_id", user.getUser_id());
						session.setAttribute("user_nickName", user.getUser_nickName());
						RequestDispatcher dispatcher = request.getRequestDispatcher("header.jsp");
						dispatcher.forward(request,  response);
						
					} else {
						RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
						request.setAttribute("errorMsg", "비밀번호 또는 아이디가 잘 못 되었습니다. 다시 입력해 주세요.");
						dispatcher.forward(request,  response);
					} 
				}
				
				
			} catch (NamingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
