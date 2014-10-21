/*
 <String method>
1. replace(): 
 1) 원본데이터를 변경하지 못한다 => String은 immutable(변경할 수 없는) 객체다.
 2) 특정 문자열을 대체하여 새 스트링 객체를 힙에 생성한다. 
 
2. intern()
 1) 현재 스트링 객체의 내용을 복제하여 String Pool에 만든다.
 2) 상수문자열을 만든다.
 */
package java01.test50;
public class StringTest02 {

	static String str1 = "Hello";
	static String str2 = "Hello";
	//중첩클래스(inner class): 특정 클래스 안에서만 사용할 클래스 라면, 그 클래스 내부에 선언하라!
	static class A{
		static String str1 = "Hello";
		static String str2 = "Hello";
	}
	static class B{
		static String str1 = "Hello";
		static String str2 = "Hello";
		
	}	
	public static void main01(String[] args) {
		if(str1 == str2){
			System.out.println("str1 == str2");
		}
		if(A.str1 == A.str2){
			System.out.println("A.str1 == A.str2");
		}
		if(B.str1 == B.str2){
			System.out.println("B.str1 == B.str2");
		}
		
		
		if(str1 == A.str1){
			System.out.println("str1 == A.str1");
		}
		if(A.str1 == B.str1){
			System.out.println("A.str1 == B.str1");
		}
		
	}
	public static void main(String[] args) {
		String str1 = "Hello"; //String Pool에 인스턴스 생성 후 주소 리턴
		String str2 = "Hello"; //기존 인스턴스의 주소 리턴
		String str3 = new String("Hello"); //Heap에 인스턴스 생성
		
		if(str1 == str2){
			System.out.println("str1 == str2");
		}
		
		if(str1 == str3){
			System.out.println("str1 == str3");
		}
		
		// 자바는 문자열을 객체로 다룬다.
		// 문자열을 비교할 때는 equals()를 사용하라
		// 객체 이기 떄문에 == 연산자는 문자열이 아닌 주소를 비교하므로 문자열 비교를 할 수 없다.
		System.out.println(str1.toString());
		System.out.println(str2.toString());
		System.out.println(str3.toString());
		
		System.out.printf("%s", str1);
	}
	
}
