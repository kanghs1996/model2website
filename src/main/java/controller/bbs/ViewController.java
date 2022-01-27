package controller.bbs;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.BbsDAO;
import model.bbs.BbsDTO;
import model.bbs.LikeBbsDAO;

public class ViewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOView");
		int no = Integer.parseInt(req.getParameter("no"));
		String id = req.getSession().getAttribute("LOGIN").toString();
		
		BbsDAO dao = new BbsDAO(getServletContext());
		LikeBbsDAO ldao = new LikeBbsDAO(getServletContext());
		int likeCount = ldao.select(no);
		dao.viewIncrease(no);
		BbsDTO dto = dao.selectOne(no);
		boolean isMyBbs = dao.isMyBbs(dto.getId(), id);
		Map<String, Integer> map = dao.prevNext(no);		
		
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		dao.close();
		//ldao.close();
		req.setAttribute("VIEWNO", "글고유번호");
		req.setAttribute("VIEWTITLE", "제목");
		req.setAttribute("VIEWCREATER", "작성자");
		req.setAttribute("VIEWCOUNT", "조회수");
		req.setAttribute("VIEWDATE", "등록일");
		req.setAttribute("VIEWCONTENT", "내용");
		req.setAttribute("record", dto);
		req.setAttribute("isMyBbs", isMyBbs);
		req.setAttribute("PREVNEXT", map);
		req.setAttribute("likeCount", likeCount);
		req.setAttribute("ldao", ldao);
		
		// 이동
		req.getRequestDispatcher("/bbs/View.jsp").forward(req, resp);
	}
}
