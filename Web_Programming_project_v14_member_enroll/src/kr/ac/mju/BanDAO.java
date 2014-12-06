package kr.ac.mju;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BanDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		return (DataSource) envCtx.lookup("jdbc/web");
	}
	
	public static Vector<BanDTO> banList() {
		Vector<BanDTO> banList = new Vector<BanDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		BanDTO ban;
		
		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from ban_list");
			
			while (rs.next()) {
				ban = new BanDTO(rs.getString("ban_id"),
						 rs.getString("ban_date"));
				banList.add(ban);
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return banList;
		
	}
	
	public static boolean youAreBan(BanDTO ban) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("insert into ban_list values (?, ?)");
			
			pstmt.setString(1, ban.getBan_id());
			pstmt.setString(2, ban.getBan_date());
			
			result = pstmt.executeUpdate();
		} catch(SQLException | NamingException e) { 
			e.printStackTrace();
		} finally {
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return (result==1);
	}
	
	public static Vector<String> banIdList() {
		Vector<String> banList = new Vector<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String id;
		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from ban_list");
			
			while (rs.next()) {
				id = new String(rs.getString("ban_id"));
				banList.add(id);
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return banList;
	}
	
	public static boolean freeBan(String id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();
			stmt = conn.prepareStatement("delete from ban_list where ban_id=?");
			stmt.setString(1,  id);
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
