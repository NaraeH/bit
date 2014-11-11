/*
 <인터페이스를 구현한 익명 이너 클래스 만들기>
 스냅샷 순서보면된당!
 */
package java002.test13;

public class Test03 {
	public static void main(String[] args) {
		
		//인터페이스를 구현한 익명 이너 클래스 만들기
		//일반 문법
		class MyClass2 extends Object implements MyInerface{

			@Override
			public void m2() {
				System.out.println("하하하하하하하1");
			}
		}
		
/*		//스냅샵1. 클래스명 제거 + 클래스 키워드 제거
		extends Object implements MyInerface{

			@Override
			public void m2() {
				System.out.println("하하하하하하하");
			}
		}
		
		//스냅샵2. Object를 상속받지 안으면 자동으로 상속받기 때문에 Object 생략
		// => 만약, 특정한 class를 상속받고 있다면, 상속을 지우면 안된다
		//하지만, inner class는 상속(extends)과 구현(implements)는 동시에 되지 않는다.
		//즉 inner class로 만들지 말고 외부에 class를 만들어야 한다.
		implements MyInerface{

			@Override
			public void m2() {
				System.out.println("하하하하하하하");
			}
		}
		
		//스냅샵3.implements keyword 생략
		MyInerface{

			@Override
			public void m2() {
				System.out.println("하하하하하하하");
			}
		}
		
		//스냅샵4.인스턴스 생성 명령어 결합
		new MyInerface{

			@Override
			public void m2() {
				System.out.println("하하하하하하하");
			}
		}
		
		//스냅샵5.바로호출
		new MyInerface(){

			@Override
			public void m2() {
				System.out.println("하하하하하하하");
			}
		}.m2();*/

		new MyInerface(){

			@Override
			public void m2() {
				System.out.println("하하하하하하하2");
			}
		}.m2();
	
	}
}
