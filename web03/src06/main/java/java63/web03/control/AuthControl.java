package java63.web03.control;

import java.util.HashMap;

import java63.web03.dao.MakerDao;
import java63.web03.dao.MemberDao;
import java63.web03.domain.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthControl{
	@Autowired MakerDao makerDao;
	@Autowired MemberDao memberDao;

	//확장자를 빼고서 dispatcher가 찾는다. 확장자를 빼거나 있거나 상관없다.
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String form(@CookieValue(/*required=false*/ defaultValue="") String uid, Model model) throws Exception {
		model.addAttribute("uid", uid);

		return "/auth/loginForm.jsp";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(
			String uid, 
			String pwd, 
			String save,
			HttpServletResponse response,
			HttpSession session
			) throws Exception {

			if(save != null){//쿠키에 저장을 해달라
				Cookie cookie = new Cookie("uid",uid);
				cookie.setMaxAge(60 * 60 * 24 *15); //유지기간 => 15일
				response.addCookie(cookie);
			}else{
				Cookie cookie = new Cookie("uid","");
				cookie.setMaxAge(0); //쿠키저장 무효화 시킨다.
				response.addCookie(cookie);
			}

			HashMap<String, String> params = new HashMap<>();
			params.put("userId", uid);
			params.put("password", pwd);
			Member member = memberDao.existUser(params);

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
	
	@RequestMapping("/logout")
	public String excute(HttpSession session) throws Exception {
		
		session.invalidate();
		return "redirect:login.do";
	}
}
