package java63.web03.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//어떤 url이 들어오더라도 .do로 끝나면 모두 이 클래스에서 처리하겠다.
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory sqlSessionFactory = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			try{
			
			WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			
			//1. 서블릿 url을 알아낸다 예)/product/list.do , proudct/view
			String servletPath = request.getServletPath();
			
			//2. url 서블릿 경로로 컨트롤 객체를 꺼낸다. 예)productListControl객체
			Object controller = appCtx.getBean(servletPath);
			
			//3. controller의 excute메서드 꺼내기(아규먼트 꺼내기)
			Method excute = controller.getClass().getMethod("excute", HttpServletRequest.class);
			
			//4. excute메서드 호출하여 리턴받기
			String viewUrl = (String)excute.invoke(controller, request);
			
			//5. 쿠키 추가하기
			ArrayList<Cookie> cookieList = (ArrayList<Cookie>) request.getAttribute("cookieList");
			if(cookieList != null){
				for(Cookie cookie:cookieList){
					response.addCookie(cookie);
				}
			}
			
			//6. 뷰 컴포넌트로 보내기 (if => redirect: , else => including)
			if(viewUrl.startsWith("redirect:")){
				response.sendRedirect(viewUrl.substring(9));
			}else{
				response.setContentType("text/html;charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response); 
			}
				
		}catch(Exception e){
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/Error.jsp");
			request.setAttribute("error", rd);

			rd.forward(request, response); 
		}
	}
}