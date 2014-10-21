/*
 < 인스턴스 블록 >
  - 인스턴스 생성 => 초기화 문장 수행 => (인스턴스 블록 실행, 생성자 실행)
  
  * 생성자로 커버가능, 사용할일 거의 없음
 */
package java01.test46;

public class ClassA {
	
	int i = 1;
	int j = 2;
	
	{
		
		System.out.println("ClassA인스턴스 블럭1...");
		i = 100;
		j = 200;
	}
	
	public ClassA() {
		System.out.println("ClassA생성자호출");
		i = 10;
		j = 20;
	}
	
	{
		System.out.println("ClassA인스턴스 블럭2...");
		i = 1000;
		j = 2000;
	}
	

}
