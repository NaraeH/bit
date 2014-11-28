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
@RequestMapping("/auth/login.do")
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
	
	//요청 쿠키정보중에서 uid라는 정보를 찾아서 넣어달라.
	//그리고 내가 request에 저장할 수 있도록 빈 model객체를 주면 내가 request에 값을 담아줄께
	
	/*@CookieValueError! The request sent by the client was syntactically incorrect. 
	   요청 헤더에서 쿠키 값을 꺼낸다.
	   기본은 필수 항목이다.
	   쿠키가 없으면 다음과 같은 에러가 뜬다.
	*해결방법
	   1) required=false( 필수여부 지정: 기본은 true ) 
	     => 혹시 uid라는 쿠키가 넘어오지 않더라도 에러를 띄우지 말아라.
	   2) defaultValue="" (기본 값 지정: 값이 없을 때 지정될 값)
	     => 만약 넘어오는 값이 없다면 defaultValue를 빈문자로 해서 띄어라.
	 
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String form(@CookieValue(/*required=false*/ defaultValue="") String uid, Model model) throws Exception {
		model.addAttribute("uid", uid);

		return "/auth/loginForm.jsp";
	}
	
	@RequestMapping(method=RequestMethod.POST)
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
}
