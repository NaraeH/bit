/*
 < 스프링사용하기 >
 */
package java63.servlets.test04;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@WebServlet(name="ContextLoaderListener")
public class ContextLoaderListener implements ServletContextListener{
	static ApplicationContext appCtx;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try{
			appCtx = new ClassPathXmlApplicationContext(new String[]{"java63/servlets/test04/application-context.xml"});
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	/*웹 어플리케이션이 종료할 때 호출되
	  => 서블릿이 사용한 자원을 해제할 때 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
