/*
 <Redirect 하기>
- 클라이언트에게 콘턴츠를 보내지 않는다.
- 응답헤더에 다시 요청 할 주소를 보낸다.
 */
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
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/test02/product/delete")
public class ProductDeleteServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory sqlSessionFactory = null;
	ProductDao productDao = null;
	
	public ProductDeleteServlet(){
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
		
		int no = Integer.parseInt(request.getParameter("no"));
		Product product = productDao.selectOne(no);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		//링크는 패키지와 상관없이 웹브라우저 주소창에 입력하는 주소값(/web02/test02/product/list)에서 상대경로를 계산하여야한다.
		//webapp의 것들을 해당 폴더 바로 밑에 루트에 저장된다(즉, web02밑에에 복사된다.)
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Refresh' content='5;url=list'>");
		out.println("<link rel='stylesheet' href='../../css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='../../css/bootstrap-theme.min.css'>");
		out.println("<link rel='stylesheet' href='../../css/common.css'>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<div class='container'>");
		out.println("<h1>삭제결과</h1>");
		
		out.println("<p>삭제하였습니다.</p>");

		out.println("</body>");
		out.println("</html>");
		
		//Redirect는 클라이언트에 재요청 URL만 보낸다.
		//따라서 이전에 출력한 콘텐츠는 취소한다.
		//버퍼에 출력된 내용은 클라이언트로 보내지 않고 버린다.
		//위의 출력문은 작성할 필요가 없다.
		
		HttpServletResponse orginResponse = (HttpServletResponse)response;
		orginResponse.sendRedirect("list");
		
		//Redirect의 응답 내용 => Location헤더에 재요청 URL이 있다.
		//클라이언트에 어떤 내용을 출력하지 않고
		//페이지를 바로 전환하는 효과를 내고 싶을 때
		
		
		
		

	}
}