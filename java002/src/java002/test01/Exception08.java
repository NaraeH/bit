/*
<finally block>
- try 블록에서 예외가 발생하든 안 하든 반드시 수행하는 블럭
- try 블럭에서 생성한 자원을 해제하는 코드를 둔다.
 */
package java002.test01;

import java.util.Scanner;

public class Exception08 {
	
	static int a;
	static int b;
	static String op;

	static class Calculator {
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
		
		try{
			printPage();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally{
			//try.. catch 블럭을 벗어나기 전에 반드시 실행하는 블럭
			System.out.println("오호라....finally");
		}
	}
}
