package model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class MemberDAO {
	// [멤버변수]
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public MemberDAO(ServletContext context) {
		try {
			// 데이타 베이스 연결하기-커넥션 풀 이용]
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup(context.getInitParameter("JNDI_ROOT") + "/KOSMO");
			conn = source.getConnection();
			System.out.println("접속성공");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}//////////////////
	
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

	// 회원가입
	public int insert(MemberDTO mbdto, String[] interests) {
		int affected = 0;
		String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, DEFAULT)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mbdto.getId());
			psmt.setString(2, mbdto.getPassword());
			psmt.setString(3, mbdto.getName());
			psmt.setString(4, mbdto.getNickname());
			psmt.setString(5, mbdto.getGender());
			psmt.setString(6, mbdto.getEducation());
			psmt.setString(7, mbdto.getIntroduction());
			affected = psmt.executeUpdate();
			if (affected == 0) {
				conn.rollback();
				return affected;
			} else {
				if (interests.length != 0) {
					sql = "INSERT INTO memberinter VALUES(SEQ_MEMBERINTER_NO.NEXTVAL, ?, ?)";
					for (String inter : interests) {
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, mbdto.getId());
						psmt.setString(2, inter);
						affected = psmt.executeUpdate();
						if (affected == 0) {
							conn.rollback();
							return affected;
						}
					}
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return affected;
	}

	// 회원 여부 판단용]
	public int isMember(String id, String password) {
		int affected = 0;
		String sql = "SELECT COUNT(*) FROM member WHERE id=? AND password=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			rs.next();
			affected = rs.getInt(1);							
		} catch (SQLException e) {
			e.printStackTrace();
			return affected;
		}
		return affected;
	}

	public Map select(String id) {
		String sql = "SELECT * FROM member WHERE id=?";
		Map map = new HashMap();
		MemberDTO dto = new MemberDTO();
		MemberInterestDTO inter = new MemberInterestDTO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setGender(rs.getString(5).trim());
				dto.setEducation(rs.getString(6));
				dto.setIntroduction(rs.getString(7));
				dto.setRegidate(rs.getDate(8));
			}
			sql = "SELECT interest FROM memberinter WHERE id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				inter.setInterest(rs.getString(1));				
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("dto", dto);
		map.put("inter", inter);
		return map;
	}
	
	public String replaceGender(String gender) {
		String rep;
		switch (gender.trim()) {
			case "man":
				rep = gender.replace("man", "남성");
				break;
			default:
				rep = gender.replace("woman", "여성");
		}		
		return rep;
	}
	
	public String replaceEducation(String education) {
		String rep;
		switch (education) {
			case "uni":
				rep = education.replace("uni", "대학교");
				break;
			case "hig":
				rep = education.replace("hig", "고등학교");
				break;
			case "mid":
				rep = education.replace("mid", "중학교");
				break;
			default:
				rep = education.replace("ele", "초등학교");
		}		
		return rep;
	}

	public int Withdrawal(String id) {
		int affected = 0;
		String sql = "DELETE member WHERE id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return affected;
	}

	public boolean isDuplication(int bifurcation, String ... dup) {
		String sql = "SELECT COUNT(*) FROM member WHERE ";		
		if(bifurcation == 1) {
			sql += "id=?";
		}else if(bifurcation == 2){
			sql += "LOWER(nickname)=LOWER(?)";
		}else {
			sql += "id=? AND LOWER(nickname)=LOWER(?)";
		}
		try {
			psmt = conn.prepareStatement(sql);
			if(bifurcation == 1 || bifurcation == 2) {
				psmt.setString(1, dup[0]);
			}else {
				psmt.setString(1, dup[0]);
				psmt.setString(2, dup[1]);
			}
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}

	public int update(MemberDTO mbdto, String[] interests) {
		int affected = 0;
		String sql = "UPDATE member SET ";
		if(mbdto.getPassword() != "") {
			sql += "password=?,";
		}
		sql += "name=?, nickname=?, gender=?, education=?, introduction=? WHERE id=?";
		try {
			psmt = conn.prepareStatement(sql);			
			if(mbdto.getPassword() != "") {
				psmt.setString(1, mbdto.getPassword());
				psmt.setString(2, mbdto.getName());
				psmt.setString(3, mbdto.getNickname());
				psmt.setString(4, mbdto.getGender());
				psmt.setString(5, mbdto.getEducation());
				psmt.setString(6, mbdto.getIntroduction());
				psmt.setString(7, mbdto.getId());
			}else {
				psmt.setString(1, mbdto.getName());
				psmt.setString(2, mbdto.getNickname());
				psmt.setString(3, mbdto.getGender());
				psmt.setString(4, mbdto.getEducation());
				psmt.setString(5, mbdto.getIntroduction());
				psmt.setString(6, mbdto.getId());
			}
			affected = psmt.executeUpdate();
			if (affected == 0) {
				conn.rollback();
				return affected;
			} else {
				if (interests.length != 0) {
					sql = "DELETE memberinter WHERE id = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, mbdto.getId());		
					affected = psmt.executeUpdate();
					if (affected == 0) {
						conn.rollback();
						return affected;
					}
					sql = "INSERT INTO memberinter VALUES(SEQ_MEMBERINTER_NO.NEXTVAL, ?, ?)";
					for (String inter : interests) {
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, mbdto.getId());
						psmt.setString(2, inter);
						affected = psmt.executeUpdate();
						if (affected == 0) {
							conn.rollback();
							return affected;
						}
					}
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return affected;
	}
}
