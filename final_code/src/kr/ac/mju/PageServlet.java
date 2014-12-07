package kr.ac.mju;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;




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
		} else if(op.equals("join")) {
			pageLocation = "member-enroll.jsp";
		} else if(op.equals("logout")) {
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
				pageLocation = "boardMain.jsp";
			}
		} else if(op.equals("searchInfo")) {
			pageLocation="boardMain.jsp";
		} else if(op.equals("newWriting")) {
			pageLocation="newWriting.jsp";
		} else if(op.equals("showHistory")) {
			String date = request.getParameter("date");
			String historyInfor[] = historyManager.getHistoryInforFromDate(date);
			request.setAttribute("title", useModify.getWriting_title());
			request.setAttribute("date", historyInfor[0]);
			request.setAttribute("writer", historyInfor[1]);
			request.setAttribute("content", historyInfor[2]);
			pageLocation = "historyPage.jsp";
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
		long currentTime = System.currentTimeMillis();  
	    SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");  
		if(isLogin!=null) {
			if(op==null) {
				FileDTO filedto = null;
			    int maxSize  = 1024*1024*10;            // 10Mbyte 제한
			    String root = request.getSession().getServletContext().getRealPath("/");	 // 웹서버 컨테이너 경로
			    String realPath = root + "\\upload\\";    // 파일 저장 경로(ex : /home/tour/web/ROOT/upload)
			    String savePath = "upload\\";
			    try {
			        MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			        String uploadFile = multi.getOriginalFileName("uploadFile");		        // 파일업로드
			        String explain = multi.getParameter("explain");
			        String fileName = simDf.format(new Date(currentTime)) +"."+ uploadFile.substring(uploadFile.lastIndexOf(".")+1);
			        File oldFile = new File(realPath+uploadFile);
			        File newFile = new File(realPath+fileName);
			        oldFile.renameTo(newFile);
			        filedto = new FileDTO(useModify.getWriting_title(), savePath+fileName, explain);
			        fileManager.createFile(filedto);
					String images = fileManager.getParsedFile(useModify.getWriting_title());
					request = requestManager.setRequestWriting(request, useModify, images);
					request.setAttribute("isInitSearch", "false");
					pageLocation = "boardMain.jsp";
			    } catch(Exception e){
			        e.printStackTrace();
			    }
			} else if(op.equals("newWriting")) {
				writing.setWriting_title(request.getParameter("writing_title"));
				if(!writingManager.isExistWriting(request.getParameter("writing_title"))) {
					writing.setWriting_content(request.getParameter("writing_content"));
					writing.setWriting_date(today);
					writing.setWriting_writer(request.getParameter("writing_writer"));
					writing.setWriting_history(historyManager.writingGetInitHistory(request.getParameter("writing_writer"), today));
					errorMsg = historyManager.createHistory(writing);								//최초 히스토리를 만듦 
					if(WritingDAO.createWriting(writing)) {
						if(errorMsg!=null) {
							pageLocation="error.jsp";
						} else {
							pageLocation="boardMain.jsp";
							writing = writingManager.pasingWritingDTO(request.getParameter("writing_title"));
							String images = fileManager.getParsedFile(writing.getWriting_title());
							request = requestManager.setRequestWriting(request, writing, images);
							request.setAttribute("isInitSearch", "false");
						}
					} else {
						pageLocation = "error.jsp";
						errorMsg = "글 쓰기에 실패했습니다.";
					}
				} else {
					pageLocation = "error.jsp";
					errorMsg = "이미 같은 이름의 글이 있습니다.";
				}
			} else if(op.equals("modify")) {				// 수정 하는 테이블이 있는 페이지로 넘어감.
				String images = fileManager.getParsedFile(useModify.getWriting_title());
				request = requestManager.setRequestWriting(request, useModify, images);
				pageLocation = "modify.jsp";
			} else if(op.equals("isModify")) {				//수정하는 테이블에서 데이터를 받아와서 디비에 저장하는 단계임.
				useModify.setWriting_date(today);
				useModify.setWriting_writer(request.getParameter("writing_writer"));
				if(writingManager.modifyWriting(useModify, request.getParameter("modify_content"))) {
					WritingDTO writingDto = null;
					writingDto = writingManager.pasingWritingDTO(useModify.getWriting_title());
					if(writingDto==null) {
						request.setAttribute("errorMsg", "검색 결과가 없습니다.");
						pageLocation="error.jsp";
					} else {
						historyManager.modifyHistory(useModify);					//히스토리 테이블의 히스토리를 수정함.
						String images = fileManager.getParsedFile(writingDto.getWriting_title());
						request = requestManager.setRequestWriting(request, writingDto, images);
						request.setAttribute("isInitSearch", "false");
						pageLocation="boardMain.jsp";
					}
				} else {
					request.setAttribute("errorMsg", "저장 결과가 없습니다.");
					pageLocation="error.jsp";
				}
			} else {											//isLogin!=null의 마지막 블럭
				if(UserIdentity.isBanId(request.getParameter("user_id"))) {
					pageLocation = "error.jsp";
					errorMsg="당신은 밴 당했습니다.";
				}
				if(request.getParameter("user_id").equals("manager")) {
					pageLocation = "manager.jsp";
				} else {
					pageLocation = "boardMain.jsp";
				}
			}
		} else {
			if(op.equals("login")) {
				if(UserIdentity.isExistIdMethod(UserDAO.getAllId(), request.getParameter("user_id"))) {		//파라미터 아이디가 데이터베이스에 있다면
					UserDTO user;
					user = UserDAO.getUserfromId( request.getParameter("user_id"));
					if(UserIdentity.isBanId(request.getParameter("user_id"))) {
						pageLocation = "error.jsp";
						errorMsg="당신은 밴 당했습니다.";
					} else {
						if(user.getUser_id().equals(request.getParameter("user_id"))) {
							if(request.getParameter("user_pwd").equals(user.getUser_pwd())) {
								HttpSession session = request.getSession();
								session.setAttribute("isLogin", "true");
								session.setAttribute("user_id", user.getUser_id());
								session.setAttribute("user_nickName", user.getUser_nickName());
								if(user.getUser_id().equals("manager")) {
									pageLocation = "manager.jsp";
								} else {
									pageLocation = "boardMain.jsp";
								}
							} else {
								pageLocation = "error.jsp";
								errorMsg="비밀번호 또는 아이디가 잘 못 되었습니다. 다시 입력해 주세요.";
							}
						}
					}
				} else {
					pageLocation = "error.jsp";
					errorMsg = "비밀번호 또는 아이디가 잘 못 되었습니다. 다시 입력해 주세요.";
				}
			} else if(op.equals("memberEnroll")) {
				if(UserIdentity.isExistIdMethod(UserDAO.getAllId(), request.getParameter("user_id"))) {
					pageLocation = "error.jsp";
					errorMsg = "이미 존재하는 아이디입니다.";
				} else {
					String temp_userId = request.getParameter("user_id");
					String temp_userPwd = request.getParameter("user_pwd");
					String temp_userNickname = request.getParameter("user_nickName");
					String temp_userEmail = request.getParameter("user_email");
					UserDTO tempUser = new UserDTO(temp_userId, temp_userEmail, temp_userNickname, today, temp_userPwd);
					UserDAO.createUser(tempUser);
					UserDTO user;
					user = UserDAO.getUserfromId(request.getParameter("user_id"));
					HttpSession session = request.getSession();
					session.setAttribute("isLogin", "true");
					session.setAttribute("user_id", user.getUser_id());
					session.setAttribute("user_nickName", user.getUser_nickName());
					pageLocation = "boardMain.jsp";
				}
			} 
		}
		
		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageLocation);
		dispatcher.forward(request,  response);
	}
}