package java002.test21.spring.exam11;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam11/application-context.xml"});

		Engine e01 = (Engine)ctx.getBean("engine");
		Engine e02 = (Engine)ctx.getBean("engine");

		if(e01 == e02){
			System.out.println("e01과 e02는 같다");
		}
	}

}
