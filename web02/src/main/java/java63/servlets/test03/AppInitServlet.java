/*
 <servlet 생성>
 => 클라이언트가 요청했을 때 생성된다. 만약, 요청이 들어오지 않는다면 servlet은 생성되지 않는다.
 
 * 클라이언트 요청이 없더라도 강제로 서블릿을 생성하는 방법
 => 웹 어플리케이션을 시작할 때 생성된다.
 => 배치 설정(xml)에 "<load-on-startup>"을 추가한다. /또는 Annotation에 loadOnStratup의 값을 준다.
      이 태그에 주는 숫자는 실행순서를 의미한다.
      여러 개의 서블릿이 있을 때 작은 수(정수만)를 갖는 서블릿이 먼저 생성된다.
 	<servlet>
		<servlet-name>hello</servlet-name>
		<servlet-class>java63.servlets.Hello</servlet-class>
 	 	<load-on-startup>1</load-on-startup>
	</servlet>
 */
package java63.servlets.test03;

import java.io.IOException;
import java.io.InputStream;
import java63.servlets.test03.dao.ProductDao;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//Servlet이기 때문에 이름만 부여한다. => url맵핑은 되지 않고, servlet에 대해 선언만 한 상태 
/*@WebServlet(
		name = "AppInitServlet",
		loadOnStartup = 1)*/
public class AppInitServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	public static ProductDao productDao = null;
	
	
	public AppInitServlet(){
		//System.out.println("AppInitServlet 생성됨");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {		
	}
	
	
	/*
	 외부 자원을 참조하는 경우 가능한 하드코딩하지 말고, 설정파일에서 읽어오는 방식으로 처리하라!
	 설정파일? web.xml
	 설정방법? <servlet>
	 			<servlet-name>...</servlet-name>
	 			<servlet-class>...</servlet-class>
	 			<init-param>
	 				<param-name>키</param-name>
	 				<param-value>값</param-value>
	 			</init-param>
	 			<init-param>
	 				<param-name>키</param-name>
	 				<param-value>값</param-value>
	 			</init-param>
	 		</servlet>
	 설정한 값을 꺼내는 방법? ServletConfig객체의 getInitParameter("키")
	 */
	@Override
	public void init() throws ServletException {
/*		try{
			InputStream inputStream = Resources.getResourceAsStream(this.getInitParameter("mybatisConfig"));
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			productDao = new ProductDao();
			productDao.setSqlSessionFactory(sqlSessionFactory);
			}catch(Exception e){
				e.printStackTrace();
			}*/
	}
}
