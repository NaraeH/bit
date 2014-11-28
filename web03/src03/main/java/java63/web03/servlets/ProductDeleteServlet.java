package java63.web03.servlets;

import java.io.IOException;
import java63.web03.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//@WebServlet("/product/delete.do")
public class ProductDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory sqlSessionFactory = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		ProductDao productDao = (ProductDao) ctx.getBean("productDao");
		
		productDao.deletePhoto(no); //prod_photo 테이블에서 먼저 삭제
		productDao.delete(no);      //그 후 products 테이블에서 삭제
		response.setContentType("text/html;charset=UTF-8");

		response.sendRedirect("list.do");

	}
}