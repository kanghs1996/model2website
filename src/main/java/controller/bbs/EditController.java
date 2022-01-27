package controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.BbsDAO;
import model.bbs.BbsDTO;

public class EditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOEdit");
		int no = Integer.parseInt(req.getParameter("no"));
		BbsDAO dao = new BbsDAO(getServletContext());
		BbsDTO dto = dao.selectOne(no);
		dao.close();
		req.setAttribute("record", dto);
		// 이동
		req.getRequestDispatcher("/bbs/Edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("title", "KOSMOEdit");
		int no = Integer.parseInt(req.getParameter("no"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		BbsDAO dao = new BbsDAO(getServletContext());
		int successOrFail = dao.updateBbs(no, title, content);
		dao.close();

		// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		req.setAttribute("SUCCFAIL", successOrFail);
		// 입력:BBSE
		req.setAttribute("WHERE", "BBSE");
		
		req.getRequestDispatcher("/bbs/Message.jsp").forward(req, resp);
	}

}
