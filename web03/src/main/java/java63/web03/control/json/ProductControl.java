//list paging 처리하기

package java63.web03.control.json;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java63.web03.dao.MakerDao;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("json.productControl") //Spring MVC의 컴포넌트임(Page Controller)을 지정할 때 사용
@RequestMapping("json/product")  
public class ProductControl{
	static final int PAGE_DEFAULT_SIZE = 5;
	
	@Autowired MakerDao makerDao;
	@Autowired ProductDao productDao;
	@Autowired ServletContext servletContext;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView form() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("makers", makerDao.selectList());
		mv.setViewName("product/productForm");
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
	public Object list(
			@RequestParam(defaultValue="1")int pageNo, 
			@RequestParam(defaultValue="5") int pageSize) throws Exception {
		
		int totalsize = productDao.totalSize();
		int maxPageNo = (totalsize / pageSize);
		if(pageSize <= 0){ pageSize = PAGE_DEFAULT_SIZE; }

		if(pageNo <= 0){ pageNo = 1; }
		if(totalsize % pageSize > 0){ maxPageNo++; }

		//if(pageNo > 1){ model.addAttribute("prevPageNo", pageNo-1); }
		
		if(pageNo > maxPageNo){
			pageNo = maxPageNo; 
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startNo", (pageNo - 1) * pageSize);
		params.put("pageSize", pageSize);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		resultMap.put("maxPageNo", maxPageNo);
		resultMap.put("currentPageNo", pageNo);
		resultMap.put("products", productDao.selectList(params));

		return resultMap;
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
		
		return "product/productView";
	}
}