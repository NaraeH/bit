package java002.test21.spring.exam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
스프링 설정
=> 호출 할 생성자 지정하기
 */
public class Test01 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam03/application-context.xml"});

		Car c1 = (Car)ctx.getBean("b01");
		System.out.println("생성자로 설정한 model 이름 => " + c1.getModel());
		
		Car c2 = (Car)ctx.getBean("b02");
		System.out.println("생성자로 설정한 model 이름 => " + c2.getModel());
		

		Car c3 = (Car)ctx.getBean("b03");
		System.out.println("생성자로 설정한 model 이름 => " + c3.getModel());
		
		Car c4 = (Car)ctx.getBean("b04");
		System.out.println("생성자로 설정한 model 이름 => " + c4.getModel());
		
		Car c5 = (Car)ctx.getBean("b05");
		System.out.println("생성자로 설정한 model 이름 => " + c5.getModel());
		System.out.println("생성자로 설정한 model CC => " + c5.getCc());
	}

}
