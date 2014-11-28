package java63.web03.control;

import java.util.ArrayList;
import java.util.HashMap;

import java63.web03.dao.MakerDao;
import java63.web03.dao.MemberDao;
import java63.web03.domain.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component("/auth/login.do")
public class LoginControl{
	@Autowired MakerDao makerDao;
	@Autowired MemberDao memberDao;

	/*
	 @RequestMapping: 요청을 처리할 메서드를 지정하는 에노테이션
	                           즉, 이 메서드를 호출해라!
	                           
	  * RequestMapping을 붙이는 이유: 현재 클래스는 Object클래스를 상속받은 것이므로 메서드는 내가 생성한 excute외 에도 
	  *                           Object의 클래스의 메서드를 갖고 있다. 
	  *                           그러므로 Component 클래스를 찾은 뒤 객체를 생성하고 어떤 메서드를 찾아야 할지 모른다.
	  *                           그러므로 RequestMapping를 붙여서 그 메서드가 실행된다고 알려주어라
	 */
	@RequestMapping
	public String excute(HttpServletRequest request) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")){
			Cookie[] cookies = request.getCookies();

			if(cookies != null){
				for(Cookie cookie:cookies){
					if(cookie.getName().equals("uid")){
						request.setAttribute("uid", cookie.getValue());
					}
				}
			}
			request.setAttribute("makers", makerDao.selectList());

			return "/auth/loginForm.jsp";
		}else{
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			String save = request.getParameter("save");

			ArrayList<Cookie> cookieList = new ArrayList<>();
			if(save != null){//쿠키에 저장을 해달라
				Cookie cookie = new Cookie("uid",uid);
				cookie.setMaxAge(60 * 60 * 24 *15); //유지기간 => 15일
				cookieList.add(cookie);
			}else{
				Cookie cookie = new Cookie("uid","");
				cookie.setMaxAge(0); //쿠키저장 무효화 시킨다.
				cookieList.add(cookie);
			}
			request.setAttribute("cookieList", cookieList);  //response에 쿠키를 담을 수 없으니 request에 담아서 보내자

			HashMap<String, String> params = new HashMap<>();
			params.put("userId", uid);
			params.put("password", pwd);
			Member member = memberDao.existUser(params);

			HttpSession session = request.getSession();
			if(member != null){
				session.setAttribute("loginUser", member);
				if(session.getAttribute("requestUrl") != null){
					return "redirect:" + (String)session.getAttribute("requestUrl");
				}else{
					return "redirect:../product/list.do";
				}
			}else{
				session.invalidate();  //기존 세션을 제거하고 새로만든다(무효화)
				return "redirect:login.do"; //로그인 폼으로 이동
			}
		}
	}
}
