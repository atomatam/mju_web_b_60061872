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
	
	public static Vector<UserDTO> getAllData() throws NamingException, SQLException {
		Vector<UserDTO> userList = new Vector<UserDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		UserDTO user;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM userdto");

			if (rs.next()) {
				user = new UserDTO(rs.getString("user_id"),
						rs.getString("user_pwd"),
						rs.getString("user_email"),
						rs.getString("user_nickName"),
						rs.getDate("user_enrollDate"));
				userList.add(user);
			}	
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return userList;
	}
	
	public static Vector<String> getAllId() throws NamingException, SQLException {
		Vector<String> idList = new Vector<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String id;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM userdto");

			while (rs.next()) {
				id = new String(rs.getString("user_id"));
				idList.add(id);
			}
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return idList;
	}
	
	public static UserDTO getUserfromId(String id) throws NamingException, SQLException {
		UserDTO user = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("SELECT * FROM userdto WHERE user_id = ?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user=new UserDTO(rs.getString("user_id"), 
						rs.getString("user_pwd"),
						rs.getString("user_email"),
						rs.getString("user_nickName"),
						rs.getDate("user_enrollDate"));
			}
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return user;
	}
}
