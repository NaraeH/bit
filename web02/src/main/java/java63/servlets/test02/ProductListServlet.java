package java63.servlets.test02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java63.servlets.test02.dao.ProductDao;
import java63.servlets.test02.domain.Product;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/test02/product/list")
public class ProductListServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	static final int PAGE_DEFAULT_SIZE = 3;  //변하지 않는 값이고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.
	
	SqlSessionFactory sqlSessionFactory = null;
	ProductDao productDao = null;
	
	public ProductListServlet(){
		try{
		String resource = "java63/servlets/test02/dao/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		productDao = new ProductDao();
		productDao.setSqlSessionFactory(sqlSessionFactory);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
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
		out.println("<link rel='stylesheet' href='../../css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='../../css/bootstrap-theme.min.css'>");
		out.println("<link rel='stylesheet' href='../../css/common.css'>");
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
		out.println("</body>");
		
		out.println("<script src='../../js/jquery-1.11.1.js'></script>");
		out.println("</html>");

	}
}