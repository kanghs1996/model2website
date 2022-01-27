package controller.bbs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.MemberDAO;
import model.bbs.MemberDTO;
import model.bbs.MemberInterestDTO;

public class MyPageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOMyPage");
		String id = req.getSession().getAttribute("LOGIN").toString();
		
		MemberDAO dao = new MemberDAO(getServletContext());
		Map map = dao.select(id);
		MemberDTO dto = (MemberDTO)map.get("dto");
		MemberInterestDTO inter = (MemberInterestDTO)map.get("inter");		
		dto.setGender(dao.replaceGender(dto.getGender()));
		dto.setEducation(dao.replaceEducation(dto.getEducation()));
		dao.close();
		
		req.setAttribute("MYID", "아이디");
		req.setAttribute("MYNAME", "이름");
		req.setAttribute("MYNICKNAME", "닉네임");
		req.setAttribute("MYGENDER", "성별");
		req.setAttribute("MYINTERREST", "관심사항");
		req.setAttribute("MYEDUCATION", "학력");
		req.setAttribute("MYINTRODUCTION", "자기소개");
		req.setAttribute("MYREGIDATE", "가입날짜");
		req.setAttribute("dto", dto);
		req.setAttribute("inter", inter.getInterest().replace("pol", "정치").replace("eco", "경제").replace("ent", "연예").trim());
		// 이동
		req.getRequestDispatcher("/bbs/MyPage.jsp").forward(req, resp);
	}
}
