package java63.web03.control;

import java63.web03.dao.MakerDao;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/product/view.do")
public class ProductViewControl{
	@Autowired MakerDao makerDao;
	@Autowired ProductDao productDao;
	
	public String excute(HttpServletRequest request) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));

		Product product = productDao.selectOne(no);
		request.setAttribute("product", product);
		request.setAttribute("photos", productDao.selectPhoto(product.getNo()));
		
		request.setAttribute("makers", makerDao.selectList());
		//request.setAttribute("makers", makerDao.selectNameList()); =>error
		
		return "/product/productView.jsp";
	}
}