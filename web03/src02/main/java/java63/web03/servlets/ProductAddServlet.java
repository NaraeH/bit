package java63.web03.servlets;

import java.io.IOException;
import java.util.Map;
import java63.web03.dao.MakerDao;
import java63.web03.dao.ProductDao;
import java63.web03.domain.Product;
import java63.web03.util.FileUploadHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/product/add.do")
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		MakerDao makerDao = (MakerDao) ctx.getBean("makerDao");
		
		request.setAttribute("makers", makerDao.selectList());
		response.setContentType("text/html;charset=UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/product/productForm.jsp");
		rd.include(request, response); 
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			//request객체를 넘겨주면서 parsing해달라고 부탁함(내가 만든 FileUploadHelper를 통해서)
			Map<String, String> paramMap = FileUploadHelper.parse(request);
	
			Product product =new Product();
			product.setName(paramMap.get("name"));
			product.setQuantity(Integer.parseInt(paramMap.get("qty")));
			product.setMakerNo(Integer.parseInt(paramMap.get("mkno")));
			product.setPhoto(paramMap.get("photo"));
			
			//스프링의 ContextLoaderListener가 준비한 ApplicationContext객체 꺼내기
			//아규먼트: 어떤 어플리케이션의 컨텍스트인지 알려주어야 한다. (누구를 위해서 준비했는가)
			//사용이유: 서블릿에서 사용할 ProductDao준비하기 위해서 
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			ProductDao productDao = (ProductDao) ctx.getBean("productDao");
			productDao.insert(product);
			productDao.insertPhoto(product);
			response.sendRedirect("list.do");
			
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/common/Error.jsp");
			request.setAttribute("error", rd);

			rd.forward(request, response); 
		}
	}
}