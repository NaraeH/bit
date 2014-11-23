package guestBook.member;

import guestBook.member.dao.MemberDao;
import guestBook.member.domain.Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 3;  //변하지 않는 값이고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.
	
	SqlSessionFactory sqlSessionFactory = null;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		System.out.println("service시작");
		int pageNo = 0;
		int pageSize = 0;
		
		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize = PAGE_DEFAULT_SIZE;
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
		out.println("<h1>제품목록</h1>");
		out.println("<p><a href='product-form.html'  class='btn btn-primary'>새제품</a></p>");
		out.println("<table class='table table-hover'>");
		out.println("<tr>");
		/*out.println("<th>#</th>");
		out.println("<th>제품</th>");
		out.println("<th>수량</th>");
		out.println("<th>제조사</th>");*/
		out.println("</tr>");
		MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
		for (Member member : memberDao.selectList(pageNo, pageSize)) {
			out.println("<tr>");
			out.println("<td><a href=view?no=" + member.getId() + ">" 
					+ member.getName()+ "</a></td>");
			out.println("</tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		
		out.println("<address class='copyright'>Copyright&copy; Bit</address>");		
		out.println("</body>");
		
		out.println("<script src='../js/jquery-1.11.1.js'></script>");
		out.println("</html>");
	}
}