/*
 <클래스 로딩>
 1) new 연산자를 사용하여 인스턴스를 만들 때
 2) Class.forName("클래스이름")을 호출하여 임의로 클래스 로딩
  */
package java01.test45;

public class Test45 {
	public static void main(String[] args) {
		//참조 변수를 선언할 때는 클래스가 로딩되지 않는다.
		System.out.println("클래스는 언제 로딩될까? => 1");
		ClassA obj1;
		System.out.println("클래스는 언제 로딩될까? => 2"); 
		
		//클래스
		obj1 = new ClassA();   //1, 2사이에 클래스 로딩되었으므로 new를 하려고 할 때 해당 클래스가 없다면 로딩되는 것을 알 수 있다.
		
		System.out.println("클래스는 언제 로딩될까? => 3"); 
		
		System.out.println(ClassA.value);
	}
}
