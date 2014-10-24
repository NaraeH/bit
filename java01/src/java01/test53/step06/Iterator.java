/*
 Iterator를 클래스에서 자격을 읨하는 interface로 바꾼다.
 - 인터페이스? => 자격, 규칙을 정의하는 문법이다.
 - 인터페이스 문법: 자격, 규칙에 대한 메서드를 선언한다. 상수클래스 변수가 올 수 있다.
                       모든 메서드는 반드시 abstract public이다.
                       변수는 반드시 public static final이다.
 */
package java01.test53.step06;

//자격을 갖추기 위해서 가져야 할 메서드를 선언
abstract public interface Iterator {
	//변수는 반드시 public static final
	
	// abstract public은 생략가능: abstract public void setList(String[] list) => void setList(String[] list)
	// 규칙이므로 100% 공개해야 한다. public 외에 다른 접근 제어자 쓸 수 없음
	// 모든 규칙은 구현하면 안된다. => 규칙의 이름만 정의하고 다른 사람이 규칙안의 내용을 지정하는 것.
	abstract public void setList(String[] list);
	abstract public boolean hasNext();
	abstract public String next();
}
