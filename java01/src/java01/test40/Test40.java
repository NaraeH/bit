/*
 <서브 클래스의 인스턴스 생성과정>
 - super class의 명령어에 따라 먼저 인스턴스 변수를 생성한 다음에 서브클래스의 명령어에 따라 인스턴스 변수를 생성한다.
  */
package java01.test40;

public class Test40 {

	public static void main(String[] args) {
		
		Car c1 = new Car("현다이", "마구타", 1997);
		Car c2 = new Car("가이아", "M5", 2500);
		
		Truck t1 = new Truck(); //기본 생성자 호출(default constructor)
		
		/*인스턴스 변수준비과정
		1) Car 클래스에 선언된 대로 인스턴스 변수를 준비한다.
		     : maker, model, cc, diesel, releaseDate, capacity
		2) Truck 클래스에 선언된 인스턴스 변수 준비
		     : weight
		3) 초기화 문장 수행(super class의 명령어부터 실행)
		4) 생성자 호출(Truck() -> Car())
		*/
		
		//[maker][model][cc][diesel][releaseDate][capacity][weight]
	}

}
