package guestBook.board;

import java.io.IOException;
import java.io.PrintWriter;

import java63.servlets.test04.dao.ProductDao;
import java63.servlets.test04.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/test04/product/list")
public class ProductListServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 3;  //변하지 않는 값이고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.
	
	SqlSessionFactory sqlSessionFactory = null;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		System.out.println("service시작");
		int pageNo = 0;
		int pageSize = 0;
		
		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize = PAGE_DEFAULT_SIZE;
		}
		
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		//링크는 패키지와 상관없이 웹브라우저 주소창에 입력하는 주소값(/web02/test02/product/list)에서 상대경로를 계산하여야한다.
		//webapp의 것들을 해당 폴더 바로 밑에 루트에 저장된다(즉, web02밑에에 복사된다.)
		out.println("<html>");
		out.println("<head>");
		
		//다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
		RequestDispatcher rd = request.getRequestDispatcher("/common/header");
		rd.include(request, response); 
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class='container'>");
		out.println("<h1>제품목록</h1>");
		out.println("<p><a href='product-form.html'  class='btn btn-primary'>새제품</a></p>");
		out.println("<table class='table table-hover'>");
		out.println("<tr>");
		out.println("<th>#</th>");
		out.println("<th>제품</th>");
		out.println("<th>수량</th>");
		out.println("<th>제조사</th>");
		out.println("</tr>");
		//for (Product product : AppInitServlet.productDao.selectList(pageNo, pageSize)) {
		//for (Product product : ContextLoaderListener.productDao.selectList(pageNo, pageSize)) {
			
			//ProductDao를 ServletContext보관소에서 꺼내는 방식을 사용
			//단점: 위의 방식보다 코드가 늘었다.
			//장점: 특정 클래스에 종속되지 않는다. 유지보수에서 더 중요!
		ProductDao productDao = (ProductDao) this.getServletContext().getAttribute("productDao");
		for (Product product : productDao.selectList(pageNo, pageSize)) {
			out.println("<tr>");
			out.println("<td>" + product.getNo()+ "</td>");
			out.println("<td><a href=view?no=" + product.getNo() + ">" 
					+ product.getName()+ "</a></td>");
			out.println("<td>" + product.getQuantity()+ "</td>");
			out.println("<td>" + product.getMakerNo()+ "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		
		//다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
		rd = request.getRequestDispatcher("/common/footer");
		rd.include(request, response); 
				
		out.println("</body>");
		
		out.println("<script src='../../js/jquery-1.11.1.js'></script>");
		out.println("</html>");
	}
}