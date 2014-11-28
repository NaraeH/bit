package java63.web03.control;

import java63.web03.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/product/delete.do")
public class ProductDeleteControl{
	@Autowired ProductDao productDao;
	
	SqlSessionFactory sqlSessionFactory = null;

	public String excute(HttpServletRequest request) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));

		productDao.deletePhoto(no); //prod_photo 테이블에서 먼저 삭제
		productDao.delete(no);      //그 후 products 테이블에서 삭제

		return "redirect:list.do"; //DispatcherServlet에서 jsp파일이 아닌 java파일로 가는 경우는 따로 처리해주자
	}
}