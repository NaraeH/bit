package java63.web03.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain nextFilter) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		// /auth는 로그인하려고 들어가는 것이므로 계속 /auth/login.do로 이동하면 안된다!!!! => /auth로 시작할 경우는 들어가지 말자
		//System.out.println((request.getServletPath().startsWith("/auth")));
		if(!(request.getServletPath().startsWith("/auth")) &&
				(request.getSession().getAttribute("loginUser") == null)){
			//../auth/login.do라고 한다면 web03이 될수도 있고 web03/a/일 수도 있다 현재 파일의 위치에 따라 다라지므로
			//따라서 상대경로가 아닌 절대 경로를 적자!!! 항상 web03/밑으로 되어야하므로!!!!
			
			//내가 view페이지로 가고 싶어서 /view를 들어갔는데 로그인하라고 떳다 -> 그 다음에 리스트로 가면 안되면 view로 이동해야 한다!!!
			//그래서 session에 원래 클라이언트가 요청한 url을 받아와서 그 곳으로 이동할 수 있도록 셋팅해주자!
			request.getSession().setAttribute("requestUrl", request.getRequestURL() + "?" + request.getQueryString());
			response.sendRedirect(request.getServletContext().getContextPath() + "/auth/login.do");
			return;
		}else{
			nextFilter.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {}
}
