package java002.test13;

//만약, 다른 패키지라면 다음과 같이 import문을 적는다.
//import java002.test01.Test13;

//static inner class import
//방법1. 정확하게 모든 정보를 지정한다.
//ex) import 패키지명.패키지명.바깥클래스명.중첩클래스명;
//import java002.test13.Test01.TopLevelInnerClass;

//방법2. 
//import static 구문을 사용하여 static 멤버를 모두 지정하기 => *
import java002.test13.Test01.*;

public class InnerClassUser {
	public static void main(String[] args) {
		
		//1. top level inner class사용
		//방법1. 이름 전체 다 쓰기
		Test01.TopLevelInnerClass p = new Test01.TopLevelInnerClass();
		
		//방법2. static class를 import하기
		TopLevelInnerClass p2 = new TopLevelInnerClass();
		
		//2. 다른 클래스의 member inner class 사용하기
		Test01.MemberInnerClass p3 = null;
		//p3 = new Test01.MemberInnerClass(); //error!! => member inner class는 static member가 아니기 때문에 클래스이름으로 접그 할 수없다.
		//참조변수를 선언 할 때는 => 바깥 클래스명.멤버이너클래스명
		
		//member inner class를 사용하려면 바깥 클래스의 instance가 필요하다.
		//instance 메서드를 호출하려면 인스턴스가 필요하듯이
		Test01 outer = new Test01();
		p3 = outer.new MemberInnerClass(); //참괴상하다! => 잘 쓰지 않는다.
		
	}

}



