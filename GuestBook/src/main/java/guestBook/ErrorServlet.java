package guestBook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/common/error")
public class ErrorServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 3;  //변하지 않는 값이o고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.
	
	SqlSessionFactory sqlSessionFactory = null;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
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
		out.println("</p>잠시 후 다시 시도하세요</p>");
		out.println("</div>");
		
		out.println("<address class='copyright'>Copyright&copy; Bit</address>");
				
		out.println("</body>");
		
		//out.println("<script src='../js/jquery-1.11.1.js'></script>");
		out.println("</html>");
		
		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.setHeader("Refresh", "5;url=list");
		
		//오류에 대한 자세한 정보는 콘솔창에 출력하라
		//사용자에게는 비밀
		Exception e = (Exception) request.getAttribute("error");
		e.printStackTrace();
	}
}