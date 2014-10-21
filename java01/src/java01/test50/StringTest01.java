/*
 <String 클래스>
 - char[] 배열에 유니코드 문자열 저장
 - 문자열을 다루는 다양하 메서드르 구비하고 있다.
 - immutable객체 => 값을 저장한 다음 변경할 수 없다.
 
 */
package java01.test50;

public class StringTest01 {
	public static void main(String[] args) {

		String str1 = new String("Hello");
		String str2 = str1;
		String str3 = new String("Hello");

		//인스턴스의 주소 비교
		// == 은 인스턴스의 주소가 같은지를 비교하는 것
		//따라서 인스턴스의 값을 비교하고 싶다면 equals()를 사용하라??????
		if (str1 == str2) {
			System.out.println("str1 == str2");
		}

		if (str1 != str3) {
			System.out.println("str1 != str3");
		}
		
		System.out.println(str1.toString());
		System.out.println(str2);
		
		//인스턴스의 값 비교
		//String 클래스는 Object로 부터 상속 받은 equals()를 오버라이딩했다.
		// => 인스턴스에 저장된 문자열이 같은지 비교하는것으로 변경함
		// => 원래의 equals()는 인스턴스가 같은지를 비교하고 있다.
		if(str1.equals(str3)){
			System.out.println("str.equals(str3)");
		}

	}

}
