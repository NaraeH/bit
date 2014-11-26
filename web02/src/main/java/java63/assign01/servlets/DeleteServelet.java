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

@WebServlet("/product/delete")
public class DeleteServelet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		try{
		
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Product product  = productDao.selectOne(Integer.parseInt(request.getParameter("no")));

		if(product == null){
			out.println("해당 인덱스의 성적정보를 찾을 수 없습니다.");
			return;
		}
		
		productDao.delete(Integer.parseInt(request.getParameter("no")));
		out.println("삭제되었습니다.");
		out.println();
		}catch(Exception e){
			
		}
		
		
	}

}
