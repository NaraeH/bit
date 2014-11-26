package java63.servlets.test05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/common/error")
public class ErrorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory sqlSessionFactory = null;
	
	//service(ServletRequest request, ServletResponse response)를 override한것 <= GenericServlet일 때의 service()메서드
	//아래의 메서드는 service(ServletRequest request, ServletResponse response) 메서드가 호출한다.
	//GET/POST/PUT등 모든 방식의 요청에 대해 처리하고 싶다면 다음과 같이 service()메서드를 제정의하라!
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
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