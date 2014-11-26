package java63.servlets.test04;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter{
	FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		
		//1.다음 필터를 실행하기 전에 해야할 일
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		System.out.println("Log찍는다.");
		System.out.println(httpServletRequest.getRemoteAddr());
		
		//2.다음 필터 실행하기
		//단, 더 이상 다음 필터가 없으면 톰캣 서버는 해당 서블릿을 실행한다.
		//호출안하면 다음 서블릿으로 가지 못한다.
		nextFilter.doFilter(request, response);
		System.out.println("LogFilter 나가기");
		
		//3.다음 필터를 실행한 후에 해야할 일
		
	}

	@Override
	public void destroy() {}

}
