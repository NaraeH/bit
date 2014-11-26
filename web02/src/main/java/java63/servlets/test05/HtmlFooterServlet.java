/*
 < Include에 사용할 서블릿 >
 HTML페이지의 페이지의 권리 안내문 출력을 만든다.
 */
package java63.servlets.test05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/common/footer")
public class HtmlFooterServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//html Entity => html의 상수
		// ex) &amp; => &
		//     &lt;  => <
		//     &gt;  => >
		//     &copy;=> (c)
		//     &reg; => (r)
		//     &yen; => 엔화
		out.println("<address class='copyright'>Copyright&copy; NaraeH</address>");

	}
}
