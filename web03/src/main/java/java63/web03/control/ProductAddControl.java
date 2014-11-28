package java63.web03.control;

import java.io.File;
import java63.web03.dao.MakerDao;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//@Component //Spring IoC 컨테이너의 기본 객체를 지정할 때 주로 사용
@Controller //Spring MVC의 컴포넌트임(Page Controller)을 지정할 때 사용
@RequestMapping("/product/add.do")  
public class ProductAddControl{
	@Autowired MakerDao makerDao;
	@Autowired ProductDao productDao;
	@Autowired ServletContext servletContext;
	
	/*
	 dispatcherServlet이 줄 수 있는 파라미터는 몇개가 있다.
	 disaptcherServlet은 해당 타입의 빈바구니를 만들어서 준다.
	 1) Map : request에 담을 값을 보관할 임시 저장소
	 2) Model: request에 담을 값을 보관할 임시 저장소
	 3) ModelAndView: request에 담을 객체와 request url을 같이 담아서 return으로 준다.
	 
	 그외는 spring reference에서 
	 "Supported method argument types"(아규먼트타입)과 Supported method return types(리턴타입)을 보자!!!
	 매우 많은 방법이 있다.
	 */
	//방법3)
		@RequestMapping(method=RequestMethod.GET)
		public ModelAndView form() throws Exception {
			ModelAndView mv = new ModelAndView();
			mv.addObject("makers", makerDao.selectList());
			mv.setViewName("/product/productForm.jsp");
			return mv;
		}
	/*
	//방법2)
	@RequestMapping(method=RequestMethod.GET)
	public String form(Model model) throws Exception {
		model.addAttribute("makers", makerDao.selectList());
		return "/product/productForm.jsp";
	}*/

	/*
	//방법1)
	@RequestMapping(method=RequestMethod.GET)
	public String form(Map<String, Object> model) throws Exception {
		model.put("makers", makerDao.selectList());
		return "/product/productForm.jsp";
	}*/


	/*내가 필요한 값이 있을 경우 아규먼트로 dispatcher로 자동으로 jsp파일을 통해 받은 파라미터(request)에서 
	 * request.getAttribute("받을값"))로 맵핑하여 값을 전달해준다.
	 * 
	 * enctype="multipart/form-data"의 경우 스프링에서 default로 처리하지 않는다.
	 * 만약, 쓰고 싶다면 그 객체를 처리할 수 있도록 "주입"시켜라 (xml파일 설정)
	 * 그리고 나서 MultipartFile로 타입을 선언해라
	 * 
	 * @RequestParam("파라미터명");
	 * => 파라미터 값을 담을 변수임을 선언
	 * => 선언하지 않아도 요청 파라미터와 이름이 같다면 기본으로 파라미터 값을 넣어 준다.
	 * => 단, multipartFile인 경우는 선언을 해주어야 한다.
	 * */
	@RequestMapping(method=RequestMethod.POST)
	public String add(
			@RequestParam("name") String name,   
			@RequestParam int qty, //=> 요청 파라미터 이름과 변수의 이름이 같다면 생략가능
			int mkno,  //@RequestMapping 에노테이션 생략가능
			@RequestParam MultipartFile photo) throws Exception {
		
		String fileuploadRealPath= servletContext.getRealPath("/fileupload");
		String filename = System.currentTimeMillis() + "_"; 
		File file = new File(fileuploadRealPath + "/" + filename);
		photo.transferTo(file);
		
		Product product =new Product();
		product.setName(name);
		product.setQuantity(qty);
		product.setMakerNo(mkno);
		product.setPhoto(filename);

		productDao.insert(product);
		productDao.insertPhoto(product);

		return "redirect:list.do";

	}
}