package java002.test21.spring.exam14;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam14/application-context.xml"});

		/*Car c01 = (Car)ctx.getBean("c01");
		System.out.println(c01);*/
		
		//만약 Car 클래스에서 @Component("c01") 지정하지 않는다면 직접 객체이름을 지정해 주어야 한다.
		Car c02 = (Car)ctx.getBean("car");
		System.out.println(c02);

	}

}
