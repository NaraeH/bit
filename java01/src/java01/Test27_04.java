/*<클래스변수>
-클래스를 로딩할 때 준비되는 변수
-Method Area영역에 준비됨
-JVM이 실행을 종료할 때까지 유지

*로컬변수:
-함수가 호출 될 때 준비되는 변수
-Stack영역에 준비 됨
-함수 호출이 끝나면 제거된다.

*인스턴스변수:
-인스턴스가 생성될 때 준비되는 변수
-Heap 영역에 준비 됨.
-garbage collector에 의해 해제되기 전까지 존재한다
*/
package java01;

import java.util.Scanner;

//단계4: class 변수 도입 => compute()와 displayResult()에서 공유할 변수 보관
public class Test27_04 {
	
	//클래스변수선언
	static int num1;
	static int num2;
	static String operator;
	static int result;
	
	static void compute(){
		switch (operator) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			result = num1 / num2;
			break;
		}
		
	}
	
	static void displayResult(){
		System.out.println("*******************************");
		System.out.println("결과값 => " + num1 + operator + num2 + " = " + result);
		System.out.println("*******************************");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("첫번째 값 입력: ");
		num1 = scanner.nextInt();

		System.out.println("두번째 값 입력: ");
		num2 = scanner.nextInt();

		System.out.println("연산자 입력: ");
		operator = scanner.next();
		
		compute();
		displayResult();

	}

}
