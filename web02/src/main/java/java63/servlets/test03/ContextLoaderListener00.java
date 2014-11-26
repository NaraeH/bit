/*
 < ServletContextListener >
  ServletContextListener는 웹 애프리케이션이 시작하거나 종료하는 이벤트에 대해 작업 수행
  
  < ServletConetext 기능>
  웹 어플리케이션 정보를 다루는 역할(모든 서블릿이 하나의 ServletContext를 사용한다.)
  컨텍스트 파라미터 파라미터 값 조회
  웹 어플리케이션의 배치 경로를 알아냄
  값을 담는 보관소 => 공통자원을 저장한다.
  웹 어플리케이션(web02프로젝트 하나)당 하나만 생성된다.
  
 */
package java63.servlets.test03;

import java.io.InputStream;
import java63.servlets.test03.dao.ProductDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet(name="ContextLoaderListener00")
public class ContextLoaderListener00 implements ServletContextListener{
	public static ProductDao productDao = null;

	/*웹 어플리케이션이 시작할 때 호출 됨(클라이언트의 요청이 왔을 때 시작하는 것 아님)
	 => 서블릿이 사용할 공통 자원을 준비
	 
	 * Context parameter
	 => 웹 어플리케이션에서 사용할 환경 변수 정의할 때 사용
	 => 모든 서블릿이 사용할 수 있다.
	 
	 * 설정방법? web.xml에 다음과 같이 설정한다.
	 <context-param>
	 	<param-name>키</param-name>
	 	<param-value>값</param-value>
	 </context-param>
	 
	 *사용 방법?
	 ServletContext의 getInitParameter(키)호출
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try{
			ServletContext ctx = sce.getServletContext();
			InputStream inputStream = Resources.getResourceAsStream(ctx.getInitParameter("mybatisConfig"));
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			productDao = new ProductDao();
			productDao.setSqlSessionFactory(sqlSessionFactory);
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
