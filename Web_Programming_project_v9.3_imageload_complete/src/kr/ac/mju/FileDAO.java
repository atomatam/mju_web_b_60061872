package kr.ac.mju;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FileDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		return (DataSource) envCtx.lookup("jdbc/web");
	}
	
	public static boolean createFile(FileDTO file) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("insert into filedto values (?, ?, ?)");
			
			pstmt.setString(1, file.getFileTitle());
			pstmt.setString(2, file.getFilePath());
			pstmt.setString(3, file.getFileExplain());
			
			result = pstmt.executeUpdate();
		} catch(SQLException | NamingException e) { 
			e.printStackTrace();
		} finally {
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return (result==1);
	}
	
	public static FileDTO getFileFromTitle(String title) {
		FileDTO file = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("SELECT * FROM filedto WHERE file_title = ?");
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				file=new FileDTO(rs.getString("file_title"), 
						rs.getString("file_path"),
						rs.getString("file_explain"));
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}  finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (pstmt != null) try{pstmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return file;
	}
	
	public static boolean updateFile(FileDTO file)  {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		try {
			DataSource ds = getDataSource();
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement(
					"UPDATE filedto " +
					"SET  file_path=? , file_explain=? " +
					"WHERE file_title=?"
					);
			stmt.setString(1,  file.getFilePath());
			stmt.setString(2, file.getFileExplain());
			stmt.setString(3, file.getFileTitle());
			
			// 수행
			result = stmt.executeUpdate();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return (result == 1);		
	}
}
