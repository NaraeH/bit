/*
<RuntimeException을 사용하여 Exception06의 고통을 해결함>
1) RuntimeException 쓰지 않으면, 
try ... catch를 하거나
throws 를 하여 상위 호출자에게 던져야 한다.

2) RuntimeException 쓰면
throws 를 하여 상위 호출자에게 던질 필요가 없다.
가장 상위 호출자만 try ... catch로 처리하면 된다.

 */
package java002.test01;

import java.util.Scanner;

public class Exception07 {
	
	static int a;
	static int b;
	static String op;

	static class Calculator {
		//Exception대신 RuntimeException을 던진다.
		//Error처럼 메서드 선언부에 지정하지 않아도 된다.
		public static int compute(int a, int b, String op){

			switch (op) {
			case "+":
				return a + b;
			case "-":
				return a - b;
			default:
				throw new RuntimeException("지원하지 않는 연산자 입니다");
			}
		}
	}
	
	public static void printContent(){
		System.out.println("-------------------");
		printResult();
		System.out.println("-------------------");
	}
	
	//이렇게 일반 메서드를 호출하듯이 printContent()를 호출할 수 있다.
	public static void printPage(){
		System.out.println("머리말 정보....");
		printContent();
		System.out.println("꼬리말 정보....");
	}

	//compute에서는 Runtime Exception을 던진다.
	//try ... catch로 처리하지 않으면, "자동!!!!!!!!!"으로 상위 호출자에게 던진다.
	public static void printResult(){
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("결과는? " + Calculator.compute(a, b, op));
		
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("a입력 => ");
		a = scanner.nextInt();
		
		System.out.println("b입력 => ");
		b = scanner.nextInt();
		
		System.out.println("연산자 입력 => ");
		op = scanner.next();
		
		//compute()에서 발생된 예외를 printResult()가 아닌 main에서 처리할 필요가 없다.
		//이렇게 처음 호출자가 통합해서 처리하면 된다. => 오류 관리가 쉽고, 코드가 간결해짐
		try{
			printPage();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
}
