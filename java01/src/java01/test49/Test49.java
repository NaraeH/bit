/*
 - 맵에 데이터를 저장할 때 문자열이 아닌 아주 특별한 객체를 사용하기
 */
package java01.test49;

import java.util.HashMap;

public class Test49 {
	// Map에 데이터를 저장할 때 특수 키를 사용하기
	public static void main(String[] args) {

		MyKey key1 = new MyKey("12341bcd", 100, 1255);
		MyKey key2 = new MyKey("12341bcd", 100, 1255);

		Integer key3 = new Integer(10);
		Integer key4 = new Integer(10);

		String key5 = new String("a");
		String key6 = new String("a");

		HashMap 냉장고2 = new HashMap();
		// Map에 데이터를 저장할 때 원리
		// key객체의 hash 값을 사용하여 저장한다.
		냉장고2.put(key3, "보약");

		// 그러고 어머니느 놀러 가셨다.
		// 아들 퇴근..

		// 키로 냉장고를 열어본다.
		// Map에서 값을 꺼낼 때 원리
		// 1) 저장할 때 사용한 키의 해시값이 같은지 조사
		// 2) equals()를 호출하여 값이 같은지 조사
		System.out.println(냉장고2.get(key4));

		System.out.println("----------------------");
		System.out.println(key1.hashCode());
		System.out.println(key2.hashCode());
		System.out.println(key3.hashCode());
		System.out.println(key4.hashCode());
		System.out.println(key5.hashCode());
		System.out.println(key6.hashCode());

		// int, String의 주소는 다르더라도 같은 값을 갖고 있다면 같은 hashCode값을 갖는다. 이유는 실행결과 밑에서
		System.out.println("-------주소 비교----------");
		System.out.println("주소: key1 == key2? => " + (key1 == key2));
		System.out.println("주소: key3 == key4? => " + (key3 == key4));
		System.out.println("주소: key5 == key6? => " + (key5 == key6));

		System.out.println("-------hashCode 비교----------");
		System.out.println("hashCode: key1 == key2? => " + (key1.hashCode() == key2.hashCode()));
		System.out.println("hashCode: key3 == key4? => " + (key3.hashCode() == key4.hashCode()));
		System.out.println("hashCode: key5 == key6? => " + (key5.hashCode() == key6.hashCode()));
		
		/*Q. 왜 Integer와 String의 값이 같다면 왜 같은 hashCode 값을 갖을까?
		Object에서 상속받은 원래 메서드는 인스턴스가 다르면 해시값도 다르도록 프로그램되어 있다.
		그런데, String 클래스와 랩퍼 클래스(Byte, Integer, ...)는 상속받은 hashCode()를 재정의 하였다.
		=> 비록 인스턴스가 다르더라도 값이 같다면 같은 해시 값을 리턴하도록 재정의 하는 것이다.
		*/
		
		System.out.println("-------equals 비교----------");
		System.out.println("equals: key1 == key2? => " + key1.equals(key2));
		System.out.println("equals: key3 == key4? => " + key3.equals(key4));
		System.out.println("equals: key5 == key6? => " + key5.equals(key6));

	}

	// Map에 데이터를 저장할 때 문자열을 키로 사용하자
	public static void main01(String[] args) {
		HashMap studentMap = new HashMap();

		// 인스턴스를 다른 곳에 저장하는 것은 불가능 참조변수를 통해 주소를 저장하는 것, 인스턴스의 값은 heap영역에 값을 가지고
		// 있다.

		studentMap.put("s001", new Student("홍길동", 10));
		studentMap.put("s002", new Student("임꺽정", 100));

		// map은 꺼낼 때 그값을 사용한다. 인덱스가 아니라
		System.out.println(studentMap.get("s001"));
		System.out.println(studentMap.get("s002"));

	}

}
