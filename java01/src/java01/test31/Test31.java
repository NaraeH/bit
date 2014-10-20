/*
  <인스턴스 메서드>
  - 인스턴스 주소를 줘야지만 호출할 수 있는 메서드
  - 문법: 인스턴스주소.메서드();
  - 해설: 인스턴스 메서드를 호출할 때 인스턴스의 주소를 앞에 준다.
  JVM은 인스턴스 변수가 어떤 클래스의 변수인지 알아낸다.
    이때, 메서드 앞에 준 인스턴스의 주소를 메서드에 넘긴다.
    메서드는 JVM이 호출할 때 넘겨준 인스턴스 주소를 내부 비밀 변수에 저장한다. 
    비밀 변수의 이름은 this
  */
package java01.test31;
//단계8: 
public class Test31 {

	public static void main(String[] args) {
		//식 2개를 동시에
		// 10 + 2 * 7 - 4 / 2 = ?
		//20 * 3 + 76 -5 =?
		
		//Calculator 클래스의 명령에 따라 준비된 메모리를  => 인스턴스
		//그 인스턴스 메모리의 주소를 저장하는 변수를 => 레퍼런스
		//method는 절대 heap 영역에 올라가지 않는다.
		Calculator c1 = new Calculator();
		Calculator c2 = new Calculator();
		
		c1.plus(10);
		c1.plus(2);
		c1.multiple(7);
		c1.minus(4);
		c1.divide(2);
		
		c2.plus(20);
		c2.multiple(3);
		c2.plus(76);
		c2.minus(5);

		
		System.out.println("c1 결과: " + c1.getResult());
		System.out.println("c2 결과: " + c2.getResult());
	}

}
