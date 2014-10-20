/*
 <boxing and unboxing>
 JDK5부터 추가
 -boxing : 기본형 값을 랩퍼 객체로 자동 생성
 -unboxing: 랩퍼 객체에서 기본 형 값을 자동 추출하는 것
 이 두가지가 자동으로 이루어 지는 것 => autoboxing
  */
package java01;

public class Test33 {

	public static void main(String[] args) {
		//boxing
		Integer i = 10; //new Integer(10); 과 같다
		
		//unboxing
		int j = i; //i.intValue()와 같다.

	}

}
