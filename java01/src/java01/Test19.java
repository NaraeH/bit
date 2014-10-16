/*
 * switch
  -특정 값에 따라 명령어의 실행을 분기할 때 사용
  -문법
      switch(4byte 이하 정수 값, byte, short, int, char)
       case 값:
               명령어...
          break;
          ...
       default:
               명령어...
          }
          -JDk 7부터는 switch 문에 문자열을 넣을 수 있다.

*/
package java01;

import java.util.Scanner;

public class Test19 {

	public static void main(String[] args) {
		
		System.out.println("나이입력: ");
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		int age = scanner.nextInt();
		
		System.out.println(age);

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
