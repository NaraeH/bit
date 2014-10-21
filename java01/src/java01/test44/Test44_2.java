/*<다형성>
 - 다형적 변수(Polymorphic variables)
 */

package java01.test44;

import java.util.ArrayList;

public class Test44_2 {
	
	public static void main(String[] args) {
		Tank obj1 = new Tank();
		Solider obj2 = new Solider();
		Vulture obj3 = new Vulture();
		
		//다형적 변수: 변수의 쓰임이 달라지는 것 => Vehicle이나 Tank 의 메서드를 둘 다 쓸 수 있다.
		//obj4 변수는 Vehicle 객체를 가리킬 때도 사용될 수 있고, 
		// Vehicle의 서브 클래스의 객체를 가르킬 때에도 사용될 수 있다.
		Vehicle obj4 = new Tank();
		Vehicle obj5 = new Vulture();
		
		//어떤 참조변수(레퍼런스)는 해당 클래스 또는 sub class의 객체를 가리킬 수 있다.
		//단, super class의 객체는 가르킬 수 없다.
		//super class는 sub class보다 기능이 적을 수 있기 때문에
		//Vehicle obj6 = new Solider(); //error => 같은 레벨의 것이지 포함관계가 아니다.
		
		//Quiz1.
		//Tank와 Vulture, Solider 객체를 저장 할 수 있는 참조변수 배열을 선언하시오.
		Mover[] m1 = new Mover[10];
		Unit[] m2 = new Unit[10];
		
		//Quiz2.
		//super class의  참조변수로 서브 클래스를 가르킬 때는 서브 클래스의 overriding이 호출된다.
		//비록 super class의 method를 호출하더라도, 서브 클래스의 오버라이딩 메서드가 있다면, 그 메서드가 호출된다. 
		Vehicle v1 = new Tank();
		v1.move(0);
		v1.test();

		
	}

}
