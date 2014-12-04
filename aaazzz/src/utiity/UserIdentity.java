package utiity;

import java.util.Vector;

public class UserIdentity {
	
	public UserIdentity() {
		
	}
	
	public static boolean isExistIdMethod(Vector<String> ids, String id) {
		for(String tempId : ids) {
			if(tempId.equals(id)) return true;
		}
		return false;
	}
}
