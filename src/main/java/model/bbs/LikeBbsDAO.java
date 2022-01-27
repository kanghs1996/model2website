package model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class LikeBbsDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	public LikeBbsDAO(ServletContext context) {
		try {
			// 데이타 베이스 연결하기-커넥션 풀 이용]
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup(context.getInitParameter("JNDI_ROOT") + "/KOSMO");
			conn = source.getConnection();
			System.out.println("접속성공");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	// 자원반납용]-사용한 커넥션 객체를 다시 풀에 반납
	public void close() {
		try {
			// 메모리 해제]
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			// 커넥션 풀에 커넥션 객체 반납-메모리 해제 아님]
			if (conn != null)
				conn.close();
			System.out.println("잘 반납되었어요");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int likeIncrease(int no, String id) {
		int affected = 0;
		int duplication = 0;
		String sql = "SELECT COUNT(*) FROM likebbs WHERE id=? AND no=?";		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, no);
			rs = psmt.executeQuery();
			rs.next();
			duplication = rs.getInt(1); 
			if(duplication != 1) {
				sql = "INSERT INTO likebbs VALUES(SEQ_LIKEBBS_NO.NEXTVAL, ?, ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setInt(2, no);
				affected = psmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

	public int select(int no) {
		int likeCount = 0;
		String sql = "SELECT COUNT(*) FROM likebbs WHERE no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			rs.next();
			likeCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likeCount;
	}
		
}
