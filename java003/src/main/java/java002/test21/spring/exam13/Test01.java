package java002.test21.spring.exam13;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam13/application-context.xml"});

		Car c01 = (Car)ctx.getBean("c01");
		System.out.println(c01);

	}

}
