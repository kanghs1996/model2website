package controller.bbs;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.MemberDAO;
import model.bbs.MemberDTO;
import model.bbs.MemberInterestDTO;

public class SignUpController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "KOSMOSignUp");
		req.getRequestDispatcher("/bbs/SignUpForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "KOSMOSignUp");
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
		boolean isIdDuplication = dao.isDuplication(1, id);
		boolean isNicknameDuplication = dao.isDuplication(2, nickname);
		if (isIdDuplication) {
			successOrFail = 0;
			req.setAttribute("SUCCFAIL", successOrFail);
			req.setAttribute("WHERE", "DUPID");
			req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
		}else if(isNicknameDuplication) {
			successOrFail = 0;
			req.setAttribute("SUCCFAIL", successOrFail);
			req.setAttribute("WHERE", "DUPNICK");
			req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
		}else {
			successOrFail = dao.insert(mbdto, interests);
			dao.close();
			// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
			req.setAttribute("SUCCFAIL", successOrFail);
			// 입력:INS
			req.setAttribute("WHERE", "INS");

			req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
		}
	}
}
