/*
<예외 발생을 먼 호출자에게 던지고자 할 때>
 - 바로 전 호출자가 아닌 더 상위의 호출자에게 오류 정보를 던지고 싶을 때 RuntimeException을 사용하라.
 
 - RuntimeException은 Exception의 자식이지만 Error처럼 동작한다.
 
 - Q.그냥 Error 사용하면 안되는가? 
 - A. Error은 시스템의 오류를 담을 때 사용한다. 따라서, Application에서 Error을 사용하는 것은 바람직하지 않다.
         그럼에도 불구하고 Error처럼 예외를 던지고 싶을 때가 있는데 그럴 경우를 대비해서 만든 클래스가 RuntimeException이다.
 */
package java002.test01;

import java.util.Scanner;

//RuntimeException을 사용하지 않을 떄 고통
// => 상위 호출자에게 오류를 던지기 위해 메서드 선언에 반드시 throws 명령을 붙여야 한다.
//ex) printCompute(), printContent(), printPage() 을 봐라
public class Exception06 {
	
	static int a;
	static int b;
	static String op;

	static class Calculator {
		// Exception 계열의 예회를 던지는 메서드는 반드시 선언부에 throws Exception처럼 지정해야 한다.
		// but, RuntimeException인 경우 선언부에 지정안해도 가능
		public static int compute(int a, int b, String op) throws Exception {

			switch (op) {
			case "+":
				return a + b;
			case "-":
				return a - b;
			default:
				throw new Exception("지원하지 않는 연산자 입니다");
			}
		}
	}
	
	//printResult()에서 던진 예외를 처리하기 싫으면 상위 호출자에게 넘긴다.
	public static void printContent() throws Exception{
		System.out.println("-------------------");
		printResult();
		System.out.println("-------------------");
	}
	
	//printContent()에서 던진 예외를 처리하기 싫으면 상위 호출자에게 넘긴다.
	public static void printPage() throws Exception{
		System.out.println("머리말 정보....");
		printContent();
		System.out.println("꼬리말 정보....");
	}

	//compute에서 발생하는 예외를 처리하기 싫으면 상위 호출자에게 넘긴다.
	//문법: 함수 선언부에 "throws 예외명" 추가
	public static void printResult() throws Exception{
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
		
		//compute()에서 발생된 예외를 printResult()가 아닌 main에서 처리한다.
		try{
			printPage();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
}
