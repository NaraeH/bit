package java63.web03.control;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import java63.web03.dao.MakerDao;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller //Spring MVC의 컴포넌트임(Page Controller)을 지정할 때 사용
@RequestMapping("/product")  
public class ProductControl{
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
	
	@RequestMapping("/delete")
	public String delete(int no) throws Exception {
		productDao.deletePhoto(no); //prod_photo 테이블에서 먼저 삭제
		productDao.delete(no);      //그 후 products 테이블에서 삭제

		return "redirect:list.do"; //DispatcherServlet에서 jsp파일이 아닌 java파일로 가는 경우는 따로 처리해주자
	}
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) throws Exception {
		int pageNo = 0;
		int pageSize = 0;

		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize = PAGE_DEFAULT_SIZE;
		}

		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startNo", (pageNo - 1) * pageSize);
		params.put("pageSize", pageSize);

		request.setAttribute("products", productDao.selectList(params));

		return "/product/productList.jsp";
	}
	
	@RequestMapping("/update.do")
	public String update(HttpServletRequest request) throws Exception {
		
		Product product =new Product();

		product.setNo(Integer.parseInt(request.getParameter("no")));
		product.setName(request.getParameter("name"));
		product.setQuantity(Integer.parseInt(request.getParameter("qty")));
		product.setMakerNo(Integer.parseInt(request.getParameter("mkno")));
		
		productDao.update(product);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/view.do")
	public String view(HttpServletRequest request) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));

		Product product = productDao.selectOne(no);
		request.setAttribute("product", product);
		request.setAttribute("photos", productDao.selectPhoto(product.getNo()));
		
		request.setAttribute("makers", makerDao.selectList());
		//request.setAttribute("makers", makerDao.selectNameList()); =>error
		
		return "/product/productView.jsp";
	}
	
}