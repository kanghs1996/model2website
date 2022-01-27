package controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOIndex");
		//이동
		req.getRequestDispatcher("/bbs/Index.jsp").forward(req, resp);
	}
}
