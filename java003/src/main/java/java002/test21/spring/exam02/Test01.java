package java002.test21.spring.exam02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
스프링 설정
=> 호출 할 생성자 지정하기
 */
public class Test01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//이전버전
		//Car c = new Car();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/spring/exam02/application-context.xml"});

		Car c1 = (Car)ctx.getBean("b01");
		Car c2 = (Car)ctx.getBean("b02");
		Car c3 = (Car)ctx.getBean("b03");
		Car c4 = (Car)ctx.getBean("b04");
		Car c5 = (Car)ctx.getBean("b05");
		//Car c6 = (Car)ctx.getBean("b06"); //존재하지 않으므로 에러
		
		Car c11 = (Car)ctx.getBean("b11");
		Car c12 = (Car)ctx.getBean("b12");
		Car c13 = (Car)ctx.getBean("b13");
		
		Car c21 = (Car)ctx.getBean("b21");
		Car c22 = (Car)ctx.getBean("b22");
		Car c23 = (Car)ctx.getBean("b23");
		
		Car c100 = (Car)ctx.getBean("b100");
		Car c101 = (Car)ctx.getBean("b101");
		Car c102 = (Car)ctx.getBean("b102");
		Car c103 = (Car)ctx.getBean("b103");

		//Bean을 설정할 때 이름을 지정하지 않으면, '패키지명 + 클래스명 + #인덱스'를 이름으로 사용한다.
		//0번 bean의 별명은 "패키지명 + 클래스명"이 된다. => 즉 0번과 인덱스 없는 것은 동일(바로 아래 두개는 동일)
		Car c31 = (Car)ctx.getBean("java002.test21.spring.exam02.Car#0");
		Car c30 = (Car)ctx.getBean("java002.test21.spring.exam02.Car");
		Car c32 = (Car)ctx.getBean("java002.test21.spring.exam02.Car#1");
		Car c33 = (Car)ctx.getBean("java002.test21.spring.exam02.Car#2");
		//Car c34 = (Car)ctx.getBean("java002.test21.spring.exam02.Car#3"); //error => 이름없는거 3개 밖에 없으므로
		
		if(c3 == c4){
			System.out.println("c3, c4 두개는 같아요");
		}
		
		if(c3 == c5){
			System.out.println("c3, c5 두개는 같아요");
		}
		
		if(c100 == c101){
			System.out.println("c100, c101 두개는 같아요");
		}
		
		if(c31 == c30){
			System.out.println("c31과 c30은 같아요");
		}
	}

}
