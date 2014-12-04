package utility;

import java.util.Vector;

public class UserIdentity {
	
	public UserIdentity() {
		
	}
	
	public static boolean isExistIdMethod(Vector<String> ids, String id) {		//ids 중에 id가 있는지 확인하는 메소드
		for(String tempId : ids) {
			if(tempId.equals(id)) return true;
		}
		return false;
	}
}
