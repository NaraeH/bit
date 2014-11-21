/*
 < 공통으로 사용하는 자원은 보통 ServletContext에 보관한다 >
 
 ServletContext => 웹 어플리케이션 시작 시 생성됨. 종료하면 제거됨.
 HttpSession => 최소 요청 시 생성. 타임아웃 또는 무효화 명령시 제거됨
 HttpServletRequest => 요청 때마다 생성. 응답후 제거됨
 PageContext => JSP에서 사용됨 각 JSP실행될 때마다 생성되고 실행 완료되면 제거됨.

  
 */
package guestBook.board;

import java.io.InputStream;
import java63.servlets.test04.dao.ProductDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet(name="ContextLoaderListener")
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try{
			ServletContext ctx = sce.getServletContext();
			InputStream inputStream = Resources.getResourceAsStream(ctx.getInitParameter("mybatisConfig"));
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			ProductDao productDao = new ProductDao();
			productDao.setSqlSessionFactory(sqlSessionFactory);
			
			//servletContext보관소에 객체저장!
			ctx.setAttribute("productDao", productDao);
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}

	/*웹 어플리케이션이 종료할 때 호출되
	  => 서블릿이 사용한 자원을 해제할 때
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
