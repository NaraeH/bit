/*
 < 클래스 로딩 >
 - 인스턴스 생성 시(최초로딩)
 - Class.forName("클래스명") 호출
 - 클래스 변수 사용 시 ex) ClassA.vlaue; <= 만약, ClassA가 로딩되어 있지 않다면, ClassA가 로딩된다.
 
 
 *java01.test47클래스 로딩을 위한 예제일뿐, 이렇게 얼키고 설키도록(상호참조) 프로그램을 절대 짜지말자!
                 => 유지보수가 힘들다!!!!!!!!!!!!!!!!!
  */
package java01.test47;

public class Test47 {
	public static void main(String[] args) {
		
		new ClassA();
		
	}

}
