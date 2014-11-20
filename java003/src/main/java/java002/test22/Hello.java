/*
 < 서블릿 배포 >
 1) 웹앱 폴더 생성(web00)
 	$tomcat-home/webapps/web00
 2) 서블릿 파일 복사
 	$web00/WEB-INF/classes/java002/test22/Hello.class
 3) DD 파일(web.xml파일)에 서블릿 정보 설정
 	<servlet></servlet>태그를 사용하여 servlet선언 -><servlet-mapping></servlet-mapping> 태그 사용하여 서블릿에 URL을 부여
 */
package java002.test22;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Hello implements Servlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()호출");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("ServletConfig()호출");
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		System.out.println("service()호출");
		
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo()호출");
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("destroy()호출");
		
	}


}
