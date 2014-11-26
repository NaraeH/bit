package java63.servlets.test05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

//@WebServlet("/common/error")
public class ErrorServlet01 extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 3;  //변하지 않는 값이고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.
	
	SqlSessionFactory sqlSessionFactory = null;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		//링크는 패키지와 상관없이 웹브라우저 주소창에 입력하는 주소값(/web02/test02/product/list)에서 상대경로를 계산하여야한다.
		//webapp의 것들을 해당 폴더 바로 밑에 루트에 저장된다(즉, web02밑에에 복사된다.)
		out.println("<html>");
		out.println("<head>");
		
		//다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
		RequestDispatcher rd = request.getRequestDispatcher("/common/header");
		rd.include(request, response); 
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class='container'>");
		out.println("</p>잠시 후 다시 시도하세요</p>");
		out.println("</div>");
		
		//다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
		rd = request.getRequestDispatcher("/common/footer");
		rd.include(request, response); 
				
		out.println("</body>");
		
		out.println("<script src='../../js/jquery-1.11.1.js'></script>");
		out.println("</html>");
		
		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.setHeader("Refresh", "5;url=list");
		
		//오류에 대한 자세한 정보는 콘솔창에 출력하라
		//사용자에게는 비밀
		Exception e = (Exception) request.getAttribute("error");
		e.printStackTrace();
	}
}