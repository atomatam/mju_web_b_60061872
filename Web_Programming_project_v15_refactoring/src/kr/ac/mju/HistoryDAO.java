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
	
	public static HistoryDTO getHistoryFromTitle(String title) {
		HistoryDTO history = null;
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;

		try {
			DataSource ds = getDataSource();		
			conn = ds.getConnection();
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery("select * from historydto where history_title ='"+title+"'");
			
			if(rs!=null) {
				while(rs.next()) {
					history = new HistoryDTO(rs.getString("history_title"),
							rs.getString("history_content"));
				}
			} else {
				return null;
			} 
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return history;
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
	
	public static boolean updateHistory(HistoryDTO history)  {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			stmt = conn.prepareStatement(
					"UPDATE historydto " +
					"SET  history_content=? " +
					"WHERE history_title=?"
					);
			stmt.setString(1,  history.getHistory_content());
			stmt.setString(2,  history.getHistory_title());
			
			result = stmt.executeUpdate();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return (result == 1);		
	}
}
