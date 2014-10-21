/*
<호출할 슈퍼클래스의 생성자를 지정하기>
- sub class에서 어떤 super class 생성자를 호출할지 지정하지 않으면,
  super class의 기본 생성자를 호출한다. => public superclass이름(){}
  
- 문법: super(호출할 생성자의 파라미터 값);
 */
package java01.test41;

public class Truck extends Car{ 

	private float weight= 1000;  //kg
	private boolean autoDump;
	
	public Truck(){
		
		//super class의 기본 생성자가 없으면 다음과 같이 다른 super class의 생성자를 명시적으로 호출해야 한다.
		super("미정", "미정",800);

		this.autoDump = true;
	}
	

}
