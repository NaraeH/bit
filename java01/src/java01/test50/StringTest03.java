/*
 <상수문자열>
 - "문자열"식으로 표현
 - 내부적으로 String 클래스의 인스턴스를 생성한다.
 - String Pool영역에 생성한다.
 - 중복 생성되지 않는다.
 
 - Method Area 영역에 클래스가 로딩되면, 각 클래스 별로 상수 문자열의 String 인스턴스가 준비된다.
 
 Method Area 영역에서 각 클래스 별로 상수 문자열을 준비하는 메모리 영역을 상수풀(Constant Pool)이라고 얘기한다.
 
  즉, Method Area에 안에 클래스가 로딩되고, 로딩된 클래스 안에 상수  문자열이 준비된다.
  
  *String Pool
  - 상수 문자열을 보관하는 메모리 영역
  - 상수 문자열은 자주 사용되기 때문에, 메모리 낭비를 줄이기 위해 String Pool이라는 영역에 통합관리한다.
  - 같은 상수문자열이 존재할 수 없다. => 중복생성되지 않는다.
 */
package java01.test50;



public class StringTest03 {
	
	public static void main(String[] args) {
		String str1 = new String("Hello");
		String str2 = str1.intern();
		String str3 = "Hello";
		
/*		if(str1 == str2){
			System.out.println("==> str1 == str2");
		}
		
		if(str2 == str3){
			System.out.println("str2 == str3");
		}*/
		test(str2);
		
	}
	
	public static void test(String str2){
		String strt1 = new String("Hello");
		String strt2 = strt1.intern();
		String strt3 = "Hello";
		
		if(strt2 == strt3){
			System.out.println("strt2 == strt3");
		}
		
		if(strt2 == str2){
			System.out.println("strt2 == str2");
		}
	}
	

	
	
	public static void main01(String[] args) {
		
		String str1 = "Hello"; //immutable객체 : 원본데이터가 바뀌지 않는다.
		String str2 = str1.replace("l", "s");
		String str3 = str1.replace("ell", "onono");
		String str4 = str1.replace("l", "s");
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		
		if(str2 == str4){
			System.out.println("==> str2 == str4");
		}
		
		if(str2.equals(str4)){
			System.out.println("str2.equals(str4)");
		}

	}
	
}
