package utility;

import java.sql.SQLException;

import javax.naming.NamingException;

import kr.ac.mju.WritingDTO;
import kr.ac.mju.WritingDAO;

public class writingManager {
	
	public static WritingDTO pasingWritingDTO(String Keyword) {
		
		WritingDTO writing = null;
		try {
			writing = WritingDAO.getWritingDTOFromKeyword(Keyword);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}		
		if(writing==null) {
			return null;
		}
		
		
		return writing;
	}
}
