package java002.test21.spring.exam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam07/application-context.xml"});

		Car c1 = (Car)ctx.getBean("b01");
		System.out.println(c1);
		
		Car c2 = (Car)ctx.getBean("b02");
		System.out.println(c2);
		
	}

}
