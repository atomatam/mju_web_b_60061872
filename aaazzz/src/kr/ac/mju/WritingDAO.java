package kr.ac.mju;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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
	
	public static WritingDTO getWritingDTOFromKeyword(String keyword) {
		WritingDTO writing = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			DataSource ds = getDataSource();
		
		
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from writingdto where writing_title ='"+keyword+"'");
			if(rs!=null) {
				while(rs.next()) {
					writing = new WritingDTO(rs.getString("writing_title"),
					rs.getString("writing_content"),
					rs.getString("writing_date"),
					rs.getString("writing_writer"),
					rs.getString("writing_history"));
				}
			} else {
				return null;
			} 
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return writing;
	}
	
	public static ArrayList<String> getAllTitle() {
		ArrayList<String> titleList = new ArrayList<String>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			DataSource ds = getDataSource();
 
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select writing_title from writingdto");
			while(rs.next()) {
				titleList.add(rs.getString("writing_title"));
			}
			if(titleList.isEmpty()) {
				return null;
			}
		} catch(SQLException | NamingException e) { 
			e.printStackTrace();
		}
		finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return titleList;
	}
	
	public static boolean createWriting(WritingDTO writing) { 
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			
			DataSource ds = getDataSource();
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement("insert into writingdto values (?, ?, ?, ?, ?)");
			pstmt.setString(1, writing.getWriting_title());
			pstmt.setString(2, writing.getWriting_content());
			pstmt.setString(3, writing.getWriting_date());
			pstmt.setString(4, writing.getWriting_writer());
			pstmt.setString(5, writing.getWriting_history());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException | NamingException e) { 
			e.printStackTrace();
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return (result == 1);
	}
}
