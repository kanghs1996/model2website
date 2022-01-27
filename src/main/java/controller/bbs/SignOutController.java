package controller.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOutController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "KOSMOSignOut");
		req.getSession().removeAttribute("LOGIN");
		resp.sendRedirect(req.getContextPath()+"/bbs/Index.kosmo");
	}
}
