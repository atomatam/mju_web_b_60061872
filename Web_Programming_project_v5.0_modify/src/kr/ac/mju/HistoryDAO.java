package kr.ac.mju;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class HistoryDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		return (DataSource) envCtx.lookup("jdbc/web");
	}
	
	public static boolean createHistory(HistoryDTO history) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("insert into historydto values (?, ?)");
			
			pstmt.setString(1, history.getHistory_title());
			pstmt.setString(2, history.getHistory_content());
			
			result = pstmt.executeUpdate();
		} catch(SQLException | NamingException e) { 
			e.printStackTrace();
		} finally {
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return (result==1);
	}
	
}
