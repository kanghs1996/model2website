package controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.BbsDAO;

public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMODelete");		
		int no = Integer.parseInt(req.getParameter("no"));
		
		BbsDAO dao = new BbsDAO(getServletContext());
		int successOrFail = dao.deleteBbs(no);
		dao.close();
		// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		req.setAttribute("SUCCFAIL", successOrFail);
		//입력:BBSW
		req.setAttribute("WHERE", "BBSD");
		
		// 이동
		req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
	}
}
