package java63.web03.control;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java63.web03.dao.MakerDao;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//@Controller //Spring MVC의 컴포넌트임(Page Controller)을 지정할 때 사용
//@RequestMapping("/product")  
public class ProductControl01{
	static Logger log = Logger.getLogger(ProductControl01.class);
	static final int PAGE_DEFAULT_SIZE = 3;
	
	@Autowired MakerDao makerDao;
	@Autowired ProductDao productDao;
	@Autowired ServletContext servletContext;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView form() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("makers", makerDao.selectList());
		mv.setViewName("/product/productForm.jsp");
		return mv;
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Product product) throws Exception {

		String fileuploadRealPath= servletContext.getRealPath("/fileupload");
		String filename = System.currentTimeMillis() + "_"; 
		File file = new File(fileuploadRealPath + "/" + filename);
		product.getPhotofile().transferTo(file);

		product.setPhoto(filename);

		productDao.insert(product);
		productDao.insertPhoto(product);

		return "redirect:list.do";

	}
	
	@RequestMapping("/delete")
	public String delete(int no) throws Exception {
		productDao.deletePhoto(no); //prod_photo 테이블에서 먼저 삭제
		productDao.delete(no);      //그 후 products 테이블에서 삭제

		return "redirect:list.do"; //DispatcherServlet에서 jsp파일이 아닌 java파일로 가는 경우는 따로 처리해주자
	}
	
	@RequestMapping("/list")
	public String list(
			@RequestParam(defaultValue="0")int pageNo, 
			@RequestParam(defaultValue="0") int pageSize,
			Model model) throws Exception {

		if(pageNo > 0){
			pageSize = PAGE_DEFAULT_SIZE;
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startNo", (pageNo - 1) * pageSize);
		params.put("pageSize", pageSize);

		model.addAttribute("products", productDao.selectList(params));

		return "/product/productList.jsp";
	}
	
	//product로 받기 위해서는 getter,setter의 이름이 호출되어야 하므로
	//select문에서 준 property이름과 jsp파일의 이름이 동일해야 한다.
	@RequestMapping("/update")
	public String update(Product product) throws Exception {
		productDao.update(product);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/view")
	public String view(int no, Model model) throws Exception {

		Product product = productDao.selectOne(no);
		model.addAttribute("product", product);
		model.addAttribute("photos", productDao.selectPhoto(product.getNo()));
		model.addAttribute("makers", makerDao.selectList());
		
		return "/product/productView.jsp";
	}
	
	//@InitBinder: 요청 파라미터 값을 도메인객체의 프로퍼티 값으로 변환해주는 변환기 등록 => 클래스가 호출될때 무조건 출력된다(따로, 호출문 없어도).
	//메서드 이름은 중요하지 않다!! @InitBinder 에노테이션이 중요할뿐!!!
	// => "야! 프론트 컨트롤러! 다음 메서드는 요청 파라미터를 값 객체에 프로퍼티 값으로 바꿔주는 변환기를 등록하는 메서드야
	//    요청을 처리하기 전에 꼭 호출해줘"
	// 호출을 하지않으면 문자열은 java.util.Date 객체로 바꿀 수가 없어서 오류가 뜬다.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		log.debug("initBinder() 호출됨");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//setLenient()
		//default는 true => 날짜가 초과되면 자동으로 계산하여 다음달이나 년으로 간다. 예외를 띄우지 않는다.
		//false => 문자열을 엄격히 검사한다.
		dateFormat.setLenient(false); 
		
		//문자열을 특정 타입으로 바꿀 변환기를 등록한다.
		binder.registerCustomEditor(
				Date.class /*어떤 타입으로 바꿀 것인가*/, 
				new CustomDateEditor(dateFormat, true) /*변환기 => 문자열을 날짜 타입으로 바꾸는 변환기 CustomDateEditor(누구를 바꿀 것인지, 빈문자 허용여부)*/);
	}
	
/*	public static void main(String[] args) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//dateFormat.setLenient(false); //default는 true => 날짜가 초과되면 자동으로 계산하여 다음달이나 년으로 간다.
		System.out.println(dateFormat.parse("2014-12-50"));
	}*/
	
}