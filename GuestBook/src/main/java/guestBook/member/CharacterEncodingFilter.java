package guestBook.member;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		
		//1.다음 필터를 실행하기 전에 해야할 일
		request.setCharacterEncoding(filterConfig.getInitParameter("charset"));
		System.out.println("요청 데이터 UTF-8설정 완료");
		
		//2.다음 필터 실행하기
		//단, 더 이상 다음 필터가 없으면 톰캣 서버는 해당 서블릿을 실행한다.
		//호출안하면 다음 서블릿으로 가지 못한다.
		nextFilter.doFilter(request, response);
		
		//3.다음 필터를 실행한 후에 해야할 일
		//모든 필터의 일이 끝난 뒤에 srevlet를 호출하고 그 다음에 리턴되면서 하는일
		System.out.println("CharacterEncodingFilter나가기");
	
		
	}

	@Override
	public void destroy() {}

}
