package guestBook.board;

import guestBook.board.dao.BoardDao;
import guestBook.board.domain.Board;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/board/update")
public class BoardUpdateServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;

	SqlSessionFactory sqlSessionFactory = null;
	BoardViewServlet bvs;// 뷰에서 선택한 uno

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		Board board = new Board();
		

		//날짜 시간 받아오기
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		sdFormat.format(nowDate);

		

		//http://localhost:8080/guestBook_js/board/update?title=aaa&content=333&name=eee&pwd=221&no=2
		
		//board.setNo(Integer.parseInt(request.getParameter("no")));
		board.setUId(bvs.no);
		board.setContent(request.getParameter("content"));
		board.setDate(sdFormat.format(nowDate));
		board.setName(request.getParameter("name"));
		board.setPwd(Integer.parseInt(request.getParameter("pwd")));
		board.setTitle(request.getParameter("title"));


		BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
		
		if(Integer.parseInt(request.getParameter("pwd")) == boardDao.selectOne(bvs.no).getPwd()){
			boardDao.update(board);
		}//입력한 비밀번호와 동일 할 경우만 업데이트


	}
}