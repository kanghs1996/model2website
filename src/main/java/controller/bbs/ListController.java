package controller.bbs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bbs.BbsDAO;
import model.bbs.BbsDTO;
import model.bbs.PagingUtil;

public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 타이틀이름 리퀘스트영역 저장
		req.setAttribute("title", "KOSMOList");
		String id = req.getSession().getAttribute("LOGIN").toString();
		if (id.length() != 0) {
			BbsDAO bdao = new BbsDAO(getServletContext());
			// 페이징을 위한 로직 시작]
			// 전체 레코드수
			int totalRecordCount = bdao.getTotalRowCount();
			// 페이지 사이즈
			int pageSize = Integer.parseInt(this.getInitParameter("PAGE_SIZE"));
			// 블락페이지
			int blockPage = Integer.parseInt(this.getInitParameter("BLOCK_PAGE"));
			// 전체 페이지수
			int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);
			// 현재 페이지 번호
			int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));
			// 시작 및 끝 ROWNUM구하기
			int start = (nowPage - 1) * pageSize + 1;
			int end = nowPage * pageSize;
			// 페이징을 위한 로직 끝]
			Map map = new HashMap();
			map.put("start", start);
			map.put("end", end);
			// 페이징관련 로직 호출
			String pagingString = PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize, blockPage, nowPage,
					req.getContextPath() + "/bbs/List.kosmo?");
			List<BbsDTO> list = bdao.selectList(map);
			bdao.close();

			req.setAttribute("list", list);
			req.setAttribute("pagingString", pagingString);
			req.setAttribute("totalRecordCount", totalRecordCount);
			req.setAttribute("nowPage", nowPage);
			req.setAttribute("pageSize", pageSize);
			System.out.println(totalRecordCount);
			// 이동
			req.getRequestDispatcher("/bbs/List.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/bbs/Index.kosmo").forward(req, resp);
		}
	}
	
}
