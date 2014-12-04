package kr.ac.mju;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class WritingDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		return (DataSource) envCtx.lookup("jdbc/web");
	}
	
	public static WritingDTO getWritingDTOFromKeyword(String keyword) throws NamingException, SQLException {
		WritingDTO writing = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from writingdto where writing_title ='"+keyword+"'");
			if(rs!=null) {
				while(rs.next()) {
					writing = new WritingDTO(rs.getString("writing_title"),
											rs.getString("writing_content"),
											rs.getDate("writing_date"),
											rs.getString("writing_writer"),
											rs.getString("writing_history"));
				}
			} else {
				return null;
			} 
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return writing;
	}
	
}
