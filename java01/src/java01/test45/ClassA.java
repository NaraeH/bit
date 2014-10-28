/*
 < static 블럭 >
 - 클래스가 로딩 => 클래스 변수 준비 => static 블럭 실행
 - 클래스가 로딩된 후 클래스 변수를 초기화 하는 용도로 사용
 - 단 클래스 로딩은 단 한번만 수행된다 => static 블럭도 단 한번만 실행된다.
  */
package java01.test45;

public class ClassA {
	static int value = 100;
	
	static{
		System.out.println("ClassA의 static 블럭실행");
		value = 200;
	}
	
	static{
		System.out.println("static 블럭이 여러개일 경우 순차적으로 실행");
	}
}
