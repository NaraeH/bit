package java01;

public class Test22 {

	public static void main(String[] args) {

		// Quiz 1 에서 100까지 4의 배수를 옆으로 출력하라!
		// 단, 20의 배수일때마다 라인을 바꿔라
		// 20이 배수이지만 60의 배수인 경우는 새라인 앞에@출력하여라 -do~while문 사용

		long startMilliSec = System.currentTimeMillis(); //시간 체크하는 함수
		
		int num = 1;

		do {
			if (num % 4 == 0) {
				System.out.print(num + "   ");
				if (num % 20 == 0) {
					System.out.println();
					if (num % 60 == 0) {
						System.out.print("@");
					}
				}
			}
			num++;

		} while (num <= 10000);
		
		long endMillisec = System.currentTimeMillis();
		
		System.out.println(endMillisec - startMilliSec);

	}

}
