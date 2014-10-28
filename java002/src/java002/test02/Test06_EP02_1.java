/*
 <재귀 호출의 이해>
 - 함수를 만들어서 호출하기
 
 => 단점: stackOverFlow 일어날 가능성 있음, 반복문보다 실행속도가 느림(depth가 깊으므로)
 */
package java002.test02;

public class Test06_EP02_1 {
	

/*	public static void main(String[] args) {
		int sum = 0;
		//Q. 1 ~ 50까지의 합을 구하라.!
		displayNo(1, sum);
		
	}
	public static void displayNo(int no, int sum){
		
		
		System.out.println(no + "=>" + sum);
		
		if(no == 51){
			return;    //재귀호출의 탈출조건: 만약 탈출조건을 명시하지 않으면 무한루프!!!!
		}else {
			sum += no;
			displayNo(no + 1, sum); //재귀호출
		}
	}*/
	
	public static void main(String[] args) {
		int sum = 0;
		//Q. 1 ~ 50까지의 합을 구하라.!
		System.out.println(displayNo(50));
		
	}
	public static int displayNo(int no){
		
		if(no == 1){
			return no;
		}else{
			return no + displayNo(no - 1); //재귀호출
		}
	}
	

}

