package utility;

import java.util.Vector;

import kr.ac.mju.BanDAO;

public class UserIdentity {
	public static boolean isExistIdMethod(Vector<String> ids, String id) {		//ids 중에 id가 있는지 확인하는 메소드
		for(String tempId : ids) {
			if(tempId.equals(id)) return true;
		}
		return false;
	}
	
	public static boolean isBanId(String id) {
		Vector<String> banList = BanDAO.banIdList();
		System.out.println("이즈 밴 아이디 메소드 안");
		for(String banId : banList) {
			if(id.equals(banId)) {
				return true;
			}
		}
		return false;
	}
}
