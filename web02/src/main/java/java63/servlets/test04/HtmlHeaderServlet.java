/*
 < Include에 사용할 서블릿 >
 HTML페이지의 헤더 부분의 출력을 맡는다.
 */
package java63.servlets.test04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/common/header")
public class HtmlHeaderServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		//웹어플리케이션의 화면에 보여주는 이름
		//web.xml의 <dispaly-name>태그 값 출력한다.
		//그리고, 이 태그의 값은 관리자 화면에 출력한다.
		//System.out.prinln(this.getServletContext().getServletContextName())
		
		//웹 어플리케이션 경로 알아내기
		//webAppName => ex) /web02
		String webAppPath = this.getServletContext().getContextPath();
		System.out.println("webAppName====>" + webAppPath);
		
		PrintWriter out = response.getWriter();
		
		//css의 경로는 실제 웹상에서 배포되는 주소를 참고로 해야한다.
		out.println("<link rel='stylesheet' href='"
				+ webAppPath
				+ "/css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='"
				+ webAppPath
				+ "/css/bootstrap-theme.min.css'>");
		out.println("<link rel='stylesheet' href='"
				+ webAppPath
				+ "/css/common.css'>");
	}

}
