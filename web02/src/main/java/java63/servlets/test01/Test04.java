/*
 <웹 브라우저가 보낸 데이터 꺼내기 >
 => request.getParameter();
 
 문제점: 톰켓 7.0이하 버전은 get으로 전달되는 데이터가 한글인 경우 깨짐(톰켓8 => 자동처리)
 
 해결방법(톰켓 7.0이하 버전)
    1)request.setCharacterEncoding("UTF-8");
    2) 1)번을 먼저 호출한 다음에 getParameter()를 호출하여야한다.
    3) 무조건 getParameter()를 호출하기 전에 1번을 수행해야 한다
       단, 한번이라도 getParameter()가 호출한 다음에 1번을 수행하면 아무런 효과가 없다. 
       
      server.xml파일의 다음 코드에 URI인코딩 속성 추가
       ex)     <Connector connectionTimeout="20000" 
    			port="8080" 
    			protocol="HTTP/1.1" 
    			redirectPort="8443"
    			URIEncodig="UTF-8"/>
 
 */
package java63.servlets.test01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/test01/Test04")
public class Test04 extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println(name.length());
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>안녕하세요 " + name + "님!</h1>");
		out.println("</body>");
		out.println("</html>");
		
		
	}
}
