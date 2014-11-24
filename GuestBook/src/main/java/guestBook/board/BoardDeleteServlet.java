package guestBook.board;

import guestBook.board.dao.BoardDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory sqlSessionFactory = null;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		int uId = Integer.parseInt(request.getParameter("uId"));
		
		BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
		boardDao.delete(no);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.sendRedirect("list?uId="+uId);
	}
}