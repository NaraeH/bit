package java002.test21.spring.exam13;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam13/application-context02.xml"});

		Car02 c01 = (Car02)ctx.getBean("c01");
		System.out.println(c01);
		
		Car03 c02 = (Car03)ctx.getBean("c02");
		System.out.println(c02);
		
		Car04 c03 = (Car04)ctx.getBean("c03");
		System.out.println(c03);

	}

}
