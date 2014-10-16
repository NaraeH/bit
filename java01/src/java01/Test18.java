/*Quiz
switch을 작성하여라
-당신의 나이가 어떻게 되는지 다음 보기에서 고르시오
1) 10대
2) 20대
3) 30대
..
5) 50대
6) 기타

*/
package java01;

import java.util.Scanner;


public class Test18 {

	public static void main(String[] args) {
		
		System.out.println("나이입력: ");
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		int age = scanner.nextInt();
		
		switch (age / 10) {

		case 1:
			System.out.println("당신은 10대입니다");
			break;
		case 2:
			System.out.println("당신은 20대입니다");
			break;
		case 3:
			System.out.println("당신은 30대입니다");
			break;
		case 4:
			System.out.println("당신은 40대입니다");
			break;
		case 5:
			System.out.println("당신은 50대입니다");
			break;
		default:
			System.out.println("당신은 기타입니다");
		}
 
	}
}
