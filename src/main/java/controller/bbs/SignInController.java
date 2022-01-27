package controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.MemberDAO;
import model.bbs.MemberDTO;

public class SignInController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "KOSMOSignIN");
		req.getRequestDispatcher("/bbs/SignIn.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "KOSMOSignIN");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");		
		
		MemberDAO dao = new MemberDAO(getServletContext());
		int successOrFail = dao.isMember(id, password);
		dao.close();
		
		// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		req.setAttribute("SUCCFAIL", successOrFail);
		//로그인-LOG
		req.setAttribute("WHERE", "LOG");

		req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);		
	}
}
