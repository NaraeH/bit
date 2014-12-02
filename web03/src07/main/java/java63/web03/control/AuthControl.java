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
import org.springframework.web.bind.annotation.SessionAttributes;
/*
 @SessionAttributes
 => Model에 저장되는 값 중에서 세션에 저장될 객체를 지정한다.
 => 사용법
  @SessionAttributes({"key","key", ...})
  */
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/auth")
//만약 Model에 loginUser라는 이름으로 값을 저장한다면
//그 값은 request에 보관하지 말고, session에 보관하라.
//그 값은 세션에 있는 값이다.
@SessionAttributes({"loginUser","requestUrl"})
public class AuthControl{
	@Autowired MakerDao makerDao;
	@Autowired MemberDao memberDao;

	//확장자를 빼고서 dispatcher가 찾는다. 확장자를 빼거나 있거나 상관없다.
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String form(@CookieValue(/*required=false*/ defaultValue="") String uid, Model model) throws Exception {
		model.addAttribute("uid", uid);

		return "auth/loginForm";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(
			String uid, 
			String pwd, 
			String save,
			String requestUrl, /*세션에 저장된 값을 달라고 하려면?*/
			HttpServletResponse response,
			Model model,
			SessionStatus status /*SessionStatus을 끝낼것인지 말것인지 결정하는 것*/) throws Exception {

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
				//@SessionAttributes로 지정된 값을 무효화시킨다.
				// * 주의: 세션전체를 무효화 시키지 않는다.(@SessionAttributes로 지정된 값만 무효화)
				//status.setComplete() => 예를 들면 로그인정보의 session만 제거한다. 내가 로그인하고나서 검색내용 등의 세션은 사라지지 않는다.
				//session.invalidate() => 세션전체를 제거한다.
				status.setComplete(); //기존 세션을 제거하고 새로만든다(무효화) session.invalidate(); 와 비슷
				return "redirect:login.do"; //로그인 폼으로 이동
			}
	}
	
	@RequestMapping("/logout")
	public String excute(HttpSession session) throws Exception {
		
		session.invalidate();
		return "redirect:login.do";
	}
}
