package utility;

import java.util.Calendar;
import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import kr.ac.mju.BanDAO;
import kr.ac.mju.BanDTO;
import kr.ac.mju.UserDAO;
import kr.ac.mju.UserDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class userManager {
	public static String getBanList() {				//밴 리스트를 넘겨줄 목록
		Vector<BanDTO> banList = BanDAO. banList();
		StringBuffer result = new StringBuffer();
		
		for(BanDTO ban : banList) {
			result.append(ban.getBan_id()+" ");
			result.append(ban.getBan_date()+" ");
			result.append("<a href=\"ManagerServlet?op=releaseBan&ban_Id="+ban.getBan_id()+"\">접근 허용</a>");
			result.append("\n");
		}
		return result.toString();
	}
	
	public static String managingAllUser() {			//유저의 목록을 스트링으로 하여 리스트로 넘겨줌.
		Vector<UserDTO> users = UserDAO.getAllData();
		StringBuffer result = new StringBuffer();
		Vector<String> banList = BanDAO.banIdList();
		if(users==null) {
			return null;
		}
		
		users = compareBanId(users, banList);
		for(UserDTO user : users) {
			if(user.getUser_id().equals("manager")) {
				
			} else {
				result.append(user.getUser_id()+" ");
				result.append(user.getUser_nickName()+" ");
				result.append(user.getUser_email()+" ");
				result.append(user.getUser_enrollDate()+" ");
				result.append("<a href=\"ManagerServlet?op=ban&ban_id="+user.getUser_id()+"\">접근 금지</a>");
				result.append("\n");
			}
		}
		return result.toString();
	}
	
	public static Vector<UserDTO> compareBanId(Vector<UserDTO> ids, Vector<String> banList) {
		
		for(int i=0; i<ids.size(); i++) {
			for(String banId : banList) {
				if(ids.get(i).getUser_id().equals(banId))
					ids.remove(i);
			}
		}
		return ids;
	}
	
	public static void managingBanList(BanDTO ban_id) {							
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String today = dateFormat.format(calendar.getTime());
		ban_id.setBan_date(today);
		BanDAO.youAreBan(ban_id);
	}
	
	public static void freeBan(String banId) {
		BanDAO.freeBan(banId);
	}
}
