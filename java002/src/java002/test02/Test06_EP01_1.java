/*
 <재귀 호출의 이해>
 - 함수를 만들어서 호출하기
 */
package java002.test02;

public class Test06_EP01_1 {
	

	public static void main(String[] args) {
		
		int sum = 0;
		
		//Q. 1 ~ 50까지 출력하라
		displayNo(1);
		
		
		//Q. 1 ~ 50까지의 합을 구하라.!
		/*for(int i = 1; i <= 50; i++){
			sum += i;
			System.out.println("합 => " + sum);
		}*/
	}
	public static void displayNo(int no){
		
		System.out.println(no);
		
		if(no == 50){
			return;    //재귀호출의 탈출조건: 만약 탈출조건을 명시하지 않으면 무한루프!!!!
		}else {
			displayNo(no + 1); //재귀호출
		}
	}

}

