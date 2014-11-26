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

@WebServlet("/product/view")
public class ViewServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=UTF-8");
		
		try{
			PrintWriter out = response.getWriter();
			int no = Integer.parseInt(request.getParameter("no"));

			Product product = productDao.selectOne(no);
			
			if (product == null) {
				out.println("해당 번호의 성적정보를 찾을 수 없습니다.");
				return;
			}

			out.println("제품번호: " + product.getNo());
			out.println("제품명: " + product.getName());
			out.println("수량: " + product.getQuantity());
			out.println("제조사번호: " + product.getMakerNo());
			
			out.println();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
