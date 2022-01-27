package controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.MemberDAO;

public class ReCheckWithdrawalController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOReCheckWithdrawal");
		// 이동
		req.getRequestDispatcher("/bbs/ReCheckWithdrawal.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "KOSMOReCheckWithdrawal");
		String id = req.getParameter("id");
		String pass = req.getParameter("password");
		MemberDAO dao = new MemberDAO(getServletContext());
		int successOrFail = dao.isMember(id, pass);
		dao.close();

		// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		req.setAttribute("SUCCFAIL", successOrFail);
		// 
		req.setAttribute("WHERE", "RCW");

		req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
	}
}
