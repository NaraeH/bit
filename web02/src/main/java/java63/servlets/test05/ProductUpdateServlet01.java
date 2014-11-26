package java63.servlets.test05;

import java.io.IOException;

import java63.servlets.test05.dao.ProductDao;
import java63.servlets.test05.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//@WebServlet("/test05/product/update")
public class ProductUpdateServlet01 extends GenericServlet{
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory sqlSessionFactory = null;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		//다음코드는 필터로 대채함
		//request.setCharacterEncoding("UTF-8");
		
		Product product =new Product();

		product.setNo(Integer.parseInt(request.getParameter("no")));
		product.setName(request.getParameter("name"));
		product.setQuantity(Integer.parseInt(request.getParameter("qty")));
		product.setMakerNo(Integer.parseInt(request.getParameter("mkno")));
		//AppInitServlet.productDao.update(product);
		//ContextLoaderListener.productDao.update(product);
		
		//ProductDao를 ServletContext보관소에서 꺼내는 방식을 사용
		//단점: 위의 방식보다 코드가 늘었다.
		//장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
		//ProductDao productDao = (ProductDao) this.getServletContext().getAttribute("productDao");
		//ProductDao productDao = (ProductDao) ContextLoaderListener.appCtx.getBean("productDao");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		ProductDao productDao = (ProductDao) ctx.getBean("productDao");
		productDao.update(product);
		
		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.sendRedirect("list");

		
	}
}