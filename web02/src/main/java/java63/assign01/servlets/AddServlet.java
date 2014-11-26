package java63.assign01.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java63.assign01.dao.ProductDao;
import java63.assign01.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/product/add")
public class AddServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();
	Product product = new Product();

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{
			product.setName(request.getParameter("name"));
			product.setQuantity(Integer.parseInt(request.getParameter("qty")));
			product.setMakerNo(Integer.parseInt(request.getParameter("mkno")));

			productDao.insert(product);
			out.println("저장하였습니다.");
			out.println();
		}catch(Exception e){
			e.printStackTrace();
			out.println("서버가 바쁩니다. 잠시 후 다시시도하세요.");
			out.println();
		}
		
	}

}
