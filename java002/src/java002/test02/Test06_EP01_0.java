/*
 <재귀 호출의 이해>
 -처음에는 반복문으로 하였다.
 */
package java002.test02;

public class Test06_EP01_0 {
	

	public static void main(String[] args) {
		
		int sum = 0;
		
		//Q. 1 ~ 50까지 출력하라
		displayNo(1);
		
	}
	public static void displayNo(int no){
		
		System.out.println(no);
/*		for(int i = no; i <= 50; i++){
			System.out.println(i);
		}*/
	}

}
