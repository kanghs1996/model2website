package controller.bbs;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.MemberDAO;
import model.bbs.MemberDTO;
import model.bbs.MemberInterestDTO;

public class MemberEditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOMemberEdit");
		String id = req.getSession().getAttribute("LOGIN").toString();

		MemberDAO dao = new MemberDAO(getServletContext());
		Map map = dao.select(id);
		MemberDTO dto = (MemberDTO) map.get("dto");
		MemberInterestDTO inter = (MemberInterestDTO) map.get("inter");
		dao.close();
		String inters[] = inter.getInterest().trim().split(" ");
		req.setAttribute("dto", dto);
		req.setAttribute("inters", inters);
		// 이동
		req.getRequestDispatcher("/bbs/MemberEditForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOMemberEdit");
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String password = req.getParameter("pass");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nick");
		String gender = req.getParameter("gender");
		String[] interests = req.getParameterValues("interest");
		String education = req.getParameter("education");
		String introduction = req.getParameter("introduction");
		int successOrFail;

		MemberDTO mbdto = new MemberDTO();
		mbdto.setId(id);
		mbdto.setPassword(password);
		mbdto.setName(name);
		mbdto.setNickname(nickname);
		mbdto.setGender(gender);
		mbdto.setEducation(education);
		mbdto.setIntroduction(introduction);
		
		MemberDAO dao = new MemberDAO(getServletContext());
		boolean isMyNickname = dao.isDuplication(3, id, nickname);
		if(!isMyNickname) {
			dao.close();
			successOrFail = 0;
			req.setAttribute("SUCCFAIL", successOrFail);
			req.setAttribute("WHERE", "DUPNICK");
			req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
		}else {
			successOrFail = dao.update(mbdto, interests);
			dao.close();
			// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
			req.setAttribute("SUCCFAIL", successOrFail);
			// 
			req.setAttribute("WHERE", "UPD");

			req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
		}
	}
}
