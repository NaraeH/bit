package java002.test21.spring.exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 Spring Bean Container 사용하기
 1) ClassPathXmlApplicationContext => 클래스 경로에서 설정 파일을 찾는다.
 2) FileSystemXmlApplicationContext => 설정 파일이 있는 경로 지정
 */
public class Test01 {

	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam01/application-context.xml"});

		Car c1 = (Car)ctx.getBean("b01");
		Car c2 = (Car)ctx.getBean("b02");
		Car c3 = (Car)ctx.getBean("b03");
		
		if(c1 != c2){
			System.out.println("c1과 c2는 다르다.");
		}
		
		if(c1 != c3){
			System.out.println("c1과 c3는 다르다.");
		}
		
		if(c2 != c3){
			System.out.println("c2과 c3는 다르다.");
		}
	}

}
