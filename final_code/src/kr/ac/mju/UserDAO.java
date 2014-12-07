package kr.ac.mju;

import java.sql.*;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		return (DataSource) envCtx.lookup("jdbc/web");
	}
	
	public static Vector<UserDTO> getAllData() {
		Vector<UserDTO> userList = new Vector<UserDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		UserDTO user;
		
		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from userdto");
			
			while (rs.next()) {
				user = new UserDTO(rs.getString("user_id"),
						rs.getString("user_email"),
						rs.getString("user_nickName"),
						rs.getString("user_enrollDate"),
						rs.getString("user_pwd"));
				userList.add(user);
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return userList;
	}
	
	public static Vector<String> getAllId() {
		Vector<String> idList = new Vector<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String id;
		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM userdto");

			while (rs.next()) {
				id = new String(rs.getString("user_id"));
				idList.add(id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return idList;
	}
	
	public static UserDTO getUserfromId(String id) {
		UserDTO user = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("SELECT * FROM userdto WHERE user_id = ?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user=new UserDTO(rs.getString("user_id"), 
						rs.getString("user_email"),
						rs.getString("user_nickName"),
						rs.getString("user_enrollDate"),
						rs.getString("user_pwd"));
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
		
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return user;
	}
	
	public static boolean createUser(UserDTO user) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("insert into userdto values (?, ?, ?, ?, ?)");
			
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getUser_email());
			pstmt.setString(3, user.getUser_nickName());
			pstmt.setString(4, user.getUser_enrollDate());
			pstmt.setString(5, user.getUser_pwd());
			
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
