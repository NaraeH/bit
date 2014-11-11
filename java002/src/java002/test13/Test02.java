/*
< anonymous inner class 만들기>
방법1 :  인스턴스의 이름을 주고 호출하기
 1) 상위 클래스의 인스턴스 만들고 이너클래스를 넣을 공간("{}") 만들기
  ex) MyClass p = new MyClass(){};
 2) Override하고 싶은 메서드가 있다면 오버라이드 하기
 3) 해당 인스턴스의 메서드 호출하기
  ex) p.m();
  
  ===========> 단점: Override외에 새로운 메서드 만드는 것 불가능
  
방법2 :  인스턴스의 이름없이 바로 호출하기
1) 상위 클래스의 인스턴스 만들고 이너클래스를 넣을 공간("{}") 만들기고 해당 메서드 호출하기
  ex) MyClass p = new MyClass(){}.m();
  
  ===========> 장점: Override외에 새로운 메서드 만드는 것 가능
 */
package java002.test13;

public class Test02 {
	public static void main(String[] args) {
		//4) anonymous inner class만들기
		//한번만 사용하는 instance를 만들 때는 익명 inner class로 만들어라
		
		//기존 클래스 상속받아서 익명 inner class만들기
		//문법: new 슈퍼클래스() { }
		//익명 inner class는 class를 별도로 정의 할 수 없다 => 이유: 이름이 없기 때문에
		//inner class를 정의하자마자 즉시 인스턴스를 생성해야한다.
		//생성자는 슈퍼 클래스의 생성자를 호출한다.
		MyClass p = new MyClass() {  //MyClass를 상속 받은 익명 서브 클래스
			@Override
			public void m() {
				super.m();   //기존 메서드 호출
				System.out.println("ok");  //출력추가
			}
			
			public void test(){
				System.out.println("난 overrid가 아니다.");
			}
		};
		
		p.m();
		//p.test(); // error=> MyClass에 test() 가 없다. 
		
		//익명 inner class에 생성자를 지정할 때 슈퍼클래스의 생성자를 지정한다.
		//즉, 다음은 MyClass의 다른 생성자를 호출하는 예이다.
		//익명클래스는 생성자를 만들수 없다. => 이름이 없기 때문에 "생성자 이름을 뭐라고 해야하는가?"의 문제점 발생
		new MyClass(30){
			@Override
			public void m() {
				super.m();
				i *= i;
				System.out.println(i);
			}
			
			public void test(){
				System.out.println("난 overrid가 아니다.");
			}
		}.test();
	}

}
