package java63.web03.control.json;

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
import org.springframework.web.bind.annotation.SessionAttributes;
/*
 @SessionAttributes
 => Model에 저장되는 값 중에서 세션에 저장될 객체를 지정한다.
 => 사용법
  @SessionAttributes({"key","key", ...})
  */
import org.springframework.web.bind.support.SessionStatus;

@Controller("json.authControl")
@RequestMapping("/json/auth")
@SessionAttributes({"loginUser","requestUrl"})
public class AuthControl{
	@Autowired MakerDao makerDao;
	@Autowired MemberDao memberDao;
	
	@RequestMapping(value="/loginUser", method=RequestMethod.GET)
	public String loginUser() throws Exception {
		return "json/auth/loginUser";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String form(@CookieValue(defaultValue="") String uid, Model model) throws Exception {
		model.addAttribute("uid", uid);

		return "auth/loginForm";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(
			String uid, 
			String pwd, 
			String save,
			String requestUrl,
			HttpServletResponse response,
			Model model,
			SessionStatus status) throws Exception {

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
				model.addAttribute("loginUser", member);
				if(requestUrl != null){
					return "redirect:" + requestUrl;
				}else{
					return "redirect:../product/list.do";
				}
			}else{
				System.out.println("멤버정보가 없다");
				status.setComplete(); 
				return "redirect:login.do";
			}
	}
	
	@RequestMapping("/logout")
	public String excute(HttpSession session) throws Exception {
		
		session.invalidate();
		return "redirect:login.do";
	}
}
