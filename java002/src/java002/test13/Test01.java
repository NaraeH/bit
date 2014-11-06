/*          
 < 클래스 종류 >
  1. 패키지 멤버 클래스 <== 일반적으로 만드는 클래스
  2.  inner class
     1) 목적: 무분별한 클래스 노출을 막기 위해
                   특정 클래스안에서만 사용하는 클래스인 경우 그 클래스 내부에서 정의함으로써, 외부에 노출되는 것을 막을 수 있다.
                   관리가 쉽다.
     2) 종류
     - top level inner class
       => static 으로 선언된 inner class
           목적: 작은 크기의 클래스들을 묶고 싶을 때 top level 이너 클래스를 사용한다.
            (작은 크기의 클래스들의 패키지에 선언하면 관리가 번거롭다.) 
     - member inner class
     - local inner class
     - anonymous inner class
 */

package java002.test13;

public class Test01 {
	static int i;   //static 변수
	int k;          // instance 변수
	
	static void x() {
		i = 100;
		//k = 9; //error => static 변수가 아니므로
	}
	void y() {
		i = 100;
		k = 9; //ok => 인스턴스 메서드나 블럭은 this라는 내장 변수가 있다.
	}
	// 1) top level inner class
	// static 으로 선언된 inner class
	// static 이기 때문에 다른 클래스에서도 사용 가능
	static class TopLevelInnerClass{
		//inner class는 바깥 클래스의 멤버에 접근 할 수 있다.
		//inner class도 메서드나 변수와 같이 그 클래스의 멤버이기 때문이다.
		//static 영역에서는 오로지 바깥 클래스의 static멤버(변수, 메서드)만 접근 할 수 있다.
		public void test(){
			i = 10;
			//k = 20; //error => static 변수가 아니므로
		}
	
	}
	
	//2) member inner class
	class MemberInnerClass{
		//member inner class는 내부적으로 바깥 class의 인스턴스 주소를 갖고 있다.
		//바깥 클래스의 인스턴스 주소 => 바깥클래스명.this
		public void test(){
			//당연히 non-static(instance) block에서는 static 멤버 사용가능
			i = 200; 
			
			//member inner class에서는 바깥 class의 instance member를 사용할 수 있다.
			k = 300; 
			
			//위 코드는 컴파일 할 때 다음과 같이 바뀐다.
			//만약, inner class의 같은 이름을 갖은 변수가 있다면 구분하기 위해 개발자가 다음과 같이 명시적으로 선언해야한다.
			Test01.this.k = 400;
			
		}
	}
	
	public static void main(String[] args) {
		
		//3) local inner class
		//특정 메서드에서만 사용가능
		
	}

}
