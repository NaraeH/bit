package java63.web03.servlets;

import java.io.IOException;
import java.util.HashMap;
import java63.web03.dao.MakerDao;
import java63.web03.dao.MemberDao;
import java63.web03.domain.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/auth/login.do")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		
		//JSP사용할 데이터는 다 컨트롤러에서 셋팅해주어라!!! => 계산해야 한다면 controller에서 다 계산해서 보내라!!
		if(cookies != null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("uid")){
					request.setAttribute("uid", cookie.getValue());
				}
			}
		}
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		MakerDao makerDao = (MakerDao) ctx.getBean("makerDao");
		
		request.setAttribute("makers", makerDao.selectList());
		response.setContentType("text/html;charset=UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/auth/loginForm.jsp");
		rd.include(request, response); 
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			String save = request.getParameter("save");
			
			System.out.println(uid);
			System.out.println(pwd);
			System.out.println(save);
			
			if(save != null){//쿠키에 저장을 해달라
				Cookie cookie = new Cookie("uid",uid);
				cookie.setMaxAge(60 * 60 * 24 *15); //유지기간 => 15일
				response.addCookie(cookie);
			}else{
				Cookie cookie = new Cookie("uid","");
				cookie.setMaxAge(0); //쿠키저장 무효화 시킨다.
				response.addCookie(cookie);
			}
		
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			MemberDao memberDao = (MemberDao) ctx.getBean("memberDao");
			
			HashMap<String, String> params = new HashMap<>();
			params.put("userId", uid);
			params.put("password", pwd);
			Member member = memberDao.existUser(params);
			
			HttpSession session = request.getSession();
			if(member != null){
				session.setAttribute("loginUser", member);
				response.sendRedirect("../product/list.do");
			}else{
				session.invalidate();  //기존 세션을 제거하고 새로만든다(무효화)
				response.sendRedirect("login.do"); //로그인 폼으로 이동
			}
			
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/common/Error.jsp");
			request.setAttribute("error", rd);

			rd.forward(request, response); 
		}
	}

}
