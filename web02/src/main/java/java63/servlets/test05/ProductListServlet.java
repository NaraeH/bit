package java63.servlets.test05;

import java.io.IOException;
import java.util.List;
import java63.servlets.test05.dao.ProductDao;
import java63.servlets.test05.domain.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/test05/product/list")
public class ProductListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 3;  //변하지 않는 값이고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.
	
	SqlSessionFactory sqlSessionFactory = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int pageNo = 0;
			int pageSize = 0;
			try{
			if(request.getParameter("pageNo") != null){
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				pageSize = PAGE_DEFAULT_SIZE;
			}
			
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			
			//WebApplicationContextUtils: 스프링에 의해 생성된 객체의 주소를 담고 있는 클래스
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			ProductDao productDao = (ProductDao) ctx.getBean("productDao");
			
			//bean으로 생성된 것은 다 꺼낼 수 있다.
			System.out.println("dataSource =>" + ctx.getBean("dataSource"));
			
			List<Product> products = productDao.selectList(pageNo, pageSize);
			request.setAttribute("products", products);
			
			response.setContentType("text/html;charset=UTF-8");
			
			//Request(요청) + Dispatcher(배달자) => 요청을 배달하는 역할
			RequestDispatcher rd = request.getRequestDispatcher("/test05/product/productList.jsp");
			rd.include(request, response); 
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/common/error");
			request.setAttribute("error", rd);

			rd.forward(request, response); 
		}
	}
}