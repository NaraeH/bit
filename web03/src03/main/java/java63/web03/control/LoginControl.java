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

@Component("/auth/login.do")
public class LoginControl{
	@Autowired MakerDao makerDao;
	@Autowired MemberDao memberDao;

	public String excute(HttpServletRequest request) throws Exception {
		System.out.println("test");
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
