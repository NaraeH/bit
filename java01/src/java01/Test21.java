/*<반복문>
 -while(조건) {명령문}
 -while(조건) {명령문1, 명령문2, ..., 명령문 n}
 
 -do{명령문} while(조건);
 -do{명령문1, 명령문2, ..., 명령문 n} while(조건);
 
 -for(초기화 명령문; 조건; 증가문) {명령문1, 명령문2, ..., 명령문 n};
 -for(변수 : 배열){명령문1, 명령문2, ..., 명령문 n}
 */
package java01;

public class Test21 {

	public static void main(String[] args) {

		// Quiz 1 에서 20까지 출력하라. 단, 3의 배수는 출력하지 말라! -while문 사용

		int num = 1;

		System.out.println("3의 배수가 아닌수를 출력하시오:");

		while (num <= 20) {
			if (num % 3 != 0) {
				System.out.println(num);
			}
			num++;
		}

	}

}
