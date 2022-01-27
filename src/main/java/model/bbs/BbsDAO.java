package model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class BbsDAO {
	// [멤버변수]
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public BbsDAO(ServletContext context) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<BbsDTO> selectList(Map map, String ... search) {
		String sql = "SELECT * FROM (SELECT t.*, ROWNUM r FROM"
				+ "(SELECT b.no, m.nickname, b.title, b.content, b.viewcount, b.regidate FROM "
				+ "bbs b join member m on b.id = m.id ";
		if(search.length != 0) {
			sql += "WHERE "; 
			switch (search[0]) {
				case "title":
					sql += "LOWER("+search[0]+")";
					break;
				case "content":
					sql += "LOWER("+search[0]+")";
					break;
				default:
					sql += "LOWER("+search[0]+")";				
			}
			sql += " LIKE LOWER('%"+search[1]+"%')";
		}		
		sql += "ORDER BY no DESC) t) WHERE R BETWEEN ? AND ?";
		System.out.println(sql);
		List<BbsDTO> list = new Vector<>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			while (rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setNo(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setViewcount(rs.getInt(5));
				dto.setRegidate(rs.getDate(6));
				list.add(dto);
			}
			System.out.println("성공입니다 ㅎㅎ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int writeBbs(String id, String title, String content) {
		int affected = 0;
		String sql = "INSERT INTO bbs VALUES(SEQ_BBS_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, title);
			psmt.setString(3, content);
			affected = psmt.executeUpdate();
			if (affected == 0) {
				conn.rollback();
			} else {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

	public BbsDTO selectOne(int no) {
		String sql = "SELECT b.no, m.nickname, b.title, b.content, b.viewcount, b.regidate FROM bbs"
				+ " b join member m on b.id = m.id WHERE no=?";
		BbsDTO dto = new BbsDTO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			while (rs.next()) {
				dto.setNo(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setViewcount(rs.getInt(5));
				dto.setRegidate(rs.getDate(6));
			}
			System.out.println("성공입니다 ㅎㅎ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public boolean isMyBbs(String nickname, String id) {
		String sql = "SELECT id FROM member WHERE nickname=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			rs.next();
			if (rs.getString(1).equals(id)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public int deleteBbs(int no) {
		int affected = 0;
		String sql = "DELETE bbs WHERE no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			affected = psmt.executeUpdate();
			if (affected == 0) {
				conn.rollback();
			} else {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

	public Map<String, Integer> prevNext(int no) {
		Map<String, Integer> map = new HashMap<>();
		String sql;
		try {
			sql = "SELECT MIN(no) FROM bbs WHERE no > ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			rs.next();
			map.put("PREV", rs.getInt(1));
			sql = "SELECT MAX(no) FROM bbs WHERE no < ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			rs.next();
			map.put("NEXT", rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	public int updateBbs(int no, String title, String content) {
		int affected = 0;
		String sql = "UPDATE bbs SET title=?, content=? WHERE no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, no);
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

	// 전체 레코드 수 얻기용]
	public int getTotalRowCount(String ... search) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM bbs";
		if(search.length != 0) {
			sql += " b join member m on b.id=m.id WHERE "; 
			switch (search[0]) {
				case "title":
					sql += "LOWER("+search[0]+")";
					break;
				case "content":
					sql += "LOWER("+search[0]+")";
					break;
				default:
					sql += "LOWER("+search[0]+")";				
			}
			sql += " LIKE LOWER('%"+search[1]+"%')";
		}
		System.out.println(sql);		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public void viewIncrease(int no) {
		String sql = "UPDATE bbs SET viewcount=viewcount+1 WHERE no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
}
