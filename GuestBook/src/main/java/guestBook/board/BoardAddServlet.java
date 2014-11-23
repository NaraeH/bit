package guestBook.board;

import guestBook.board.dao.BoardDao;
import guestBook.board.domain.Board;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/add")
public class BoardAddServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	BoardViewServlet bvs;// 뷰에서 선택한 uid


	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		//http://localhost:8080/guestBook_js/board/add?content=aaa&name=bbb&pwd=999&title=ooo&uid=2
		Board board = new Board();

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		//날짜 시간 받아오기
		//DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		sdFormat.format(nowDate);
		
		
		
		board.setContent(request.getParameter("content"));
		board.setDate(sdFormat.format(nowDate));
		board.setName(request.getParameter("name"));
		board.setPwd(Integer.parseInt(request.getParameter("pwd")));
		board.setTitle(request.getParameter("title"));
		board.setUId(bvs.no);
		//board.setUid(Integer.parseInt(request.getParameter("uid")));
		//board.setUid();
		
		BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
		try{
			boardDao.insert(board);
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/common/error");
			request.setAttribute("error", rd);

			rd.forward(request, response); 
			e.printStackTrace();
		}
		
	}
}