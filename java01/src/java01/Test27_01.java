/*
  <메서드(함수)>
- 특정 기능을 수행하는 명령어를 묶어 놓은 것
- 파라미터: 기능을 수행하는데 필요한 정보를 전달
- 리턴 값: 기능을 수행한 후 결과를 전달
- 문법
   공개범위 스태틱여부 리턴 타입 메서드명(타입 변수명, 타입 변수명, ...){
            실행문;
        return 리턴값;
   }
   ex)
   int plus(int i, int b){
        return i + b;
   }
 */
package java01;

import java.util.Scanner;

//주제: 사용자로부터 두 개의 값과 연산자를 입력받아 계산한 후 그 결과를 출력 실행예) 값1=10 값=20 연산자=+ 10+20=30입니다. 
//단계1: 메서드 도입 전
public class Test27_01 {

	public static void main(String[] args) {
		// 3번. switch 문을 사용하여 계산 결과를 result 변수에 담는다.
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

		System.out.println("결과값 => " + num1 + operator + num2 + " = " + result);

		/*
		 * //2번. 값 입력받기
		 * Scanner scanner = new Scanner(System.in);
		 * 
		 * System.out.println("첫번째 값 입력: "); int num1 = scanner.nextInt();
		 * 
		 * System.out.println("두번째 값 입력: "); int num2 = scanner.nextInt();
		 * 
		 * System.out.println("연산자    입력: "); String operator = scanner.next();
		 * 
		 * System.out.println(" 값1 : " + num1); System.out.println(" 값2 : " +
		 * num2); System.out.println("연산자: " + operator);
		 * System.out.println(num1 + operator + num2 + " = " + (num1+num2));
		 */

		/*
		  //1번. 임의의 숫자 집어넣고 출력해보기
		 * int num1=0, num2=0; char operator = '+';
		 * 
		 * System.out.println(" 값1 : " + num1); System.out.println(" 값2 : " +
		 * num2); System.out.println("연산자: " + operator);
		 * System.out.println(num1 + operator + num2 + " = " + (num1+num2));
		 */

	}

}
