package controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.MemberDAO;

public class WithdrawalController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOWithdrawal");
		String id = req.getSession().getAttribute("LOGIN").toString();
		MemberDAO dao = new MemberDAO(getServletContext()); 
		int successOrFail = dao.Withdrawal(id);
		dao.close();
		// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		req.setAttribute("SUCCFAIL", successOrFail);
		
		if(successOrFail == 1) {
			req.getSession().removeAttribute("LOGIN");
			req.getServletContext().removeAttribute("check_remember");
			req.getServletContext().removeAttribute("remember_id");
		}
		// 이동
		req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
	}
}
