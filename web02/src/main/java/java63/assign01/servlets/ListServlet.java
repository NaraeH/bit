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

@WebServlet("/product/list")
public class ListServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=UTF-8");
		try{
			PrintWriter out = response.getWriter();
			int pageNo = 0;
			int pageSize= 0;
			
			if(request.getParameter("pageNo") != null){
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				pageSize = 3;
			}

			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}

			for (Product product : productDao.selectList(pageNo, pageSize)) {
				out.printf("%-3d %-20s %7d %-3d\n",
						product.getNo(),
						product.getName(),
						product.getQuantity(),
						product.getMakerNo());
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		
	}

}
