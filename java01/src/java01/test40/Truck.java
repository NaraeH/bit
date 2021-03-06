/*
  <상속>
  - 목표 : 기존의 코드 재사용
  - 기존의 기능을 추가하거나 변경할 때 기존의 코드를 손대지 않고 하는 방법
  - 굳이 소스가 없어도 된다.
  - 문법:
    class 새로만들클래스 extends 기존클래스 {
           추가할 인스턴스 변수
           추가할 메소드(연산자)
    }
  - super class(parent class): 상속을 해주는 클래스
  - sub class(child class): 상속을 받는 클래스
  - super class의 모든 변수와 메소드를 가져오는 것은 아니다. 단지, super class에 대한 정보(링크)를 갖고 있을 뿐이다.
      상속은 완전하게 복사를 의미하지 않음. => sub class를 사용하려면 반드시 super class가 존재하여야 한다.
 */
package java01.test40;

public class Truck extends Car{ 

	private float weight= 1000;  //kg
	private boolean autoDump;
	
	//개발자가 클래스에 대해 생성자를 만들지 않으면, 컴파일러가 자동으로 기본 생성자를 만들어준다. => public Truck(){}
	public Truck(){
		//생성자를 정의할 때 super 클래스의 생성자를 호출하는 문장을 작성하지 않으면, "super();"라고 슈퍼 클래스의 기본 생성자를 호출하는 문장이 자동으로 추가된다.
		// => 그래서, sub class의 생성자가 실행되기 전에 반드시 super class의 생성자가 먼저 실행된다.
		// *주의점: super class의 생성자를 호출하는 문장이 제일 먼저 와야한다. => 그렇지 않다면 오류
		this.autoDump = true;
	}
	

}
