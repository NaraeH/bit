package guestBook.board;

import guestBook.board.dao.BoardDao;
import guestBook.board.domain.Board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.session.SqlSessionFactory;
@WebServlet("/board/list")
public class BoardListServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 5;  //변하지 않는 값이고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.

	SqlSessionFactory sqlSessionFactory = null;
	
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		System.out.println("service시작");
		int pageNo = 0;
		int pageSize = 0;
		
		int uId = Integer.parseInt(request.getParameter("uId"));
		

		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize =  PAGE_DEFAULT_SIZE;
		}

		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='../css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='../css/bootstrap-theme.min.css'>");
		out.println("<link rel='stylesheet' href='../css/common.css'>");
		out.println("<link rel='stylesheet' href='//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css'>");
		out.println("</head>");
		out.println("<body>");

		out.println("<div class='container'>");
		out.println("<h1>방명록</h1>");

		//-----------------------------------------------
		
		//Board board2 = boardDao.selectOne(no);
		//System.out.println(no);
		

		out.println("<div>");
		out.println("<form class='form-horizontal' role='form' action='add' method='get' >");

		out.println("	<table class='table' cellpadding='10' class='table table-bordered'>");
		out.println("		<tr>");
		out.println("			<td rowspan='3'>");
		out.println("				<div class='headPhoto'>");
  	out.println("					<img width ='178px' src='../member/photo/"
  																	+ uId + ".jpg' />");
		out.println("</div></td>");
		out.println("			<td colspan='2'><input type='text' size='58' maxlength='50' ");
		out.println("				class='form-control' id='title' name='title' placeholder='제목'>");
		out.println("		</tr>");
		out.println("		<tr>");
		out.println("			<td><textarea rows='6' cols='50' name='content'");
		out.println("					maxlength='255' id='content'></textarea></td>");
		out.println("		<input type='text' hidden='' id='uId' name='uId' value='"+uId+"'>");
		out.println("				<tr><td colspan='2'>");
		out.println("					<div style='float: left;>");
		out.println("						<div class='col-lg-4'>");
		out.println("							<input type='text' size='10' maxlength='10' class='form-control input-sm'");
		out.println("								id='name' name='name' placeholder='글쓴이'>");
		out.println("						</div>");
		out.println("						<div class='col-lg-4'>");
		out.println("							<input type='password' size='10' maxlength='10'");
		out.println("							class='form-control input-sm' id='pwd' name='pwd' placeholder='비밀번호'>");
		out.println("						</div>");
		out.println("						<div><button id='btnAdd' type='submit' class='btn btn-primary'>등록</button>");
		out.println("					<button id='btnCancel' type='button' class='btn btn-primary'>취소</button>");
		out.println("			</div></div>");
		out.println("	</table>");

		out.println("</form>");
		
		
		
		
		out.println("	</table>");

		out.println("</form>");

	
		//-----------------------------------------

		out.println("<div id='accordion'>");
		
		BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
		for (Board board : boardDao.selectList(pageNo, pageSize,uId)) {

			//board.setUId(Integer.parseInt(request.getParameter("no")));
			//out.println("<input type='text' hidden='' id='no' name='no'>");
			out.println("<h3><div><div class='title'>" + board.getName() + " : " + board.getTitle() + "</div>" 
					+ "<div text-align: right;'>" + board.getDate());
			out.println("</div></div></h3>");
			
			out.println("<div>");
			out.println("<div style='float: left;'>");
			out.println("			<textarea rows='6' cols='65' name='content'");
			out.println("					maxlength='255' id='content' >"+board.getContent()+"</textarea>");
//			out.println("	<div class='content'>");
//			out.println("  <p>" + board.getContent() + "</p>");
//			out.println("	</div>");
			out.println("</div>");
			out.println("<div text-align: right;'>");
			out.println("<a href='/guestbook/board/view?no=" + board.getNo() +"&uId="+ uId
					+ "' class='btn btn-info btn-xs'>ㅁ</a>");
			out.println("</div>");
			
//			out.println("<p><a href='/GuestBook/board/delete?no=" + board.getNo() +"&uId="+ uId
//					+ "' class='btn btn-info'>삭제</a></p>");
			//out.println(" 	<button id='btnModify' type='button' class='btn btn-info'>수정</button>");
			out.println("</div>");

		}
		out.println("</div>");

		out.println("<address class='copyright'>Copyright&copy; Bit</address>");
		out.println("</body>");

		out.println("<script src='../js/jquery-1.11.1.js'></script>");
		out.println("<script src='//code.jquery.com/ui/1.11.2/jquery-ui.js'></script>");
		out.println("<script>");
		out.println("$(function() { $( '#accordion' ).accordion();});");

		/*
		out.println("		$('#btnModify').click(function(){");
    out.println("    if (window.confirm('수정하시겠습니까?')) {");
		out.println("			location.href = 'view?no=" + "';"); // no????
    out.println("    }");
		out.println("		});");		*/
		out.println("</script>");

		//-----------------------------

		out.println("<script>");

		out.println("$('#btnCancel').click(function() {");
		out.println("	history.back();");
		out.println("});");
		out.println("$('#btnAdd').click(function() {");
		out.println("	if ($('#name').val().length == 0) {");
		out.println("		alert('이름은 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#pwd').val().length == 0) {");
		out.println("		alert('암호는 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#title').val().length == 0) {");
		out.println("		alert('글 제목은 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("	if ($('#content').val().length == 0) {");
		out.println("		alert('글 내용은 필수 입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
		out.println("});");
		out.println("</script>");
		//-------------------------------

		out.println("</html>");
	}
}