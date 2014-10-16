package java01;

import java.util.Scanner;

//단계2: 결과 출력을 별도의 메소드로 분리
public class Test27_02 {
	
	//void: 리턴하는 결과가 없는 경우
	static void displayResult(int num1, int num2,String operator, int result){
		System.out.println("**************************************************");
		System.out.println("결과값 => " + num1 + operator + num2 + " = " + result);
		System.out.println("**************************************************");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("첫번째 값 입력: ");
		int num1 = scanner.nextInt();

		System.out.println("두번째 값 입력: ");
		int num2 = scanner.nextInt();

		System.out.println("연산자    입력: ");
		String operator = scanner.next();

		int result = 0;
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

		//메소드 호출: 반드시 호출할 때 정확하게 파라미터를 넘겨야 한다.
		displayResult(num1, num2, operator, result);

	}

}
