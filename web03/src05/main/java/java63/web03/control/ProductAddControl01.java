package java63.web03.control;

import java.util.Map;
import java63.web03.dao.MakerDao;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
 @RequestMapping
 => 메서드에 URL을 연결한다
 => 이 애노테이션의 기능을 완전히 기능을 활용하려면, 이 에노테이션 처리기를 등록해야 한다.
 */
//@Component는 해당 클래스의 객체를 만들라는 것
//방법1) @Component("/product/add.do")
//방법2) @Component
//방법3) @Component @RequestMapping("/product/add.do")
//방법4) @Component @RequestMapping("/product")
//@Component
@RequestMapping("/product/add.do")  //두개의 메서드가 둘다 value가 "/product/add.do"로 공통되므로 클래스위에 선언하자.
public class ProductAddControl01{
	@Autowired MakerDao makerDao;
	@Autowired ProductDao productDao;


	//방법2) 	@RequestMapping("/product/add.do")
	//방법4) @RequestMapping("/add.do")
	@RequestMapping(method=RequestMethod.GET)
	public String form(HttpServletRequest request) throws Exception {
		request.setAttribute("makers", makerDao.selectList());
		return "/product/productForm.jsp";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		Map<String, String> paramMap = null; //임시 조치
		//Map<String, String> paramMap = FileUploadHelper.parse(request); //FileUploadHelper 삭제 후 오류 발생 문제 때문에 주석으로 막음

		Product product =new Product();
		product.setName(paramMap.get("name"));
		product.setQuantity(Integer.parseInt(paramMap.get("qty")));
		product.setMakerNo(Integer.parseInt(paramMap.get("mkno")));
		product.setPhoto(paramMap.get("photo"));

		productDao.insert(product);
		productDao.insertPhoto(product);

		return "redirect:list.do";

	}
}