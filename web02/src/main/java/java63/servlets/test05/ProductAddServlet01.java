package java63.servlets.test05;

import java.io.IOException;
import java63.servlets.test05.dao.ProductDao;
import java63.servlets.test05.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//@WebServlet("/test05/product/add")
public class ProductAddServlet01 extends GenericServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		Product product =new Product();

		product.setName(request.getParameter("name"));
		product.setQuantity(Integer.parseInt(request.getParameter("qty")));
		product.setMakerNo(Integer.parseInt(request.getParameter("mkno")));
		

		//스프링의 ContextLoaderListener가 준비한 ApplicationContext객체 꺼내기
		//아규먼트: 어떤 어플리케이션의 컨텍스트인지 알려주어야 한다. (누구를 위해서 준비했는가)
		//사용이유: 서블릿에서 사용할 ProductDao준비하기 위해서 
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		ProductDao productDao = (ProductDao) ctx.getBean("productDao");
		try{
			productDao.insert(product);
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/common/error");
			request.setAttribute("error", rd);

			rd.forward(request, response); 
		}
		
		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.sendRedirect("list");
	}
}