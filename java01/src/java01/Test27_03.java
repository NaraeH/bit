package java01;

import java.util.Scanner;

//단계3: 메서드 도입 => 값을 계산하는 명령어를 별도의 메서드로 분리하라!
//메서드명: compute
//파라미터: num1, num2, operator
//리턴: num1과 num2의 연산결과
public class Test27_03 {
	
	static int compute(int num1, int num2, String operator){
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
		
		return result;
	}
	
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
		
		result = compute(num1, num2, operator);
		displayResult(num1, num2, operator, result);

	}

}
