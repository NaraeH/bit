/*
<요청 파라미터 값 다루기>
=> 웹 브라우저가 전송한 데이터
 
 */
package java63.servlets.test01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/test01/Test05")
public class Test05 extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		int reuslt = 0;
		String op = request.getParameter("op");
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		

		
		switch(op){
		case "+":
			reuslt = a + b;
			break;
		case "-":
			reuslt = a - b;
			break;
		case "*":
			reuslt = a * b;
			break;
		case "/": 
			reuslt = a - b;
			break;
		}
		
		out.println("<html>");
		out.println("<body>");
		out.println("<p>" + a + op + b + " = " + reuslt + "</p>");
		out.println("</body>");
		out.println("</html>");
		
		
	}
}
