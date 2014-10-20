/*
 <암시적 형변환(implicit type conversion)
 연산을 수행 할 때 JVM의 규칙에 따라 자동으로 형변환 되는 것.
 - 규칙
  byte, short, char => int => long => float => double 
  (데이터를 잃지 않기 위해 더 큰 타입으로 변환)
  (연산의 기본은 int)
 - 왜 형변환을 하는가?
 연산을 수행하려면 두 개의 값이 같은 데이터 형이어야 한다.
  
 
  */
package java01;

public class Test35 {

	public static void main(String[] args) {
		// 암시적 형변환
		byte b= 10;
		byte b2 =10;
		short s = 10;
		char c=10;
		int i =10;
		long l = 10L;
		float f = 10.0f;
		double dd = 10.0;
		
		//byte sum1 = b + s + c + i + l + f + dd; //error: can not convert form double to byte
		//byte sum2 = b + s + c + i + l + f;   //error: can not convert form float to byte
		//byte sum3 = b + b2;   //error: can not convert form int to byte => 연산의 기본은 int
		
		//int와 int의 연산결과는 int이다.
				
		int x = 5;
		int y = 2;
		
		System.out.println(x/y);
		System.out.println((float)x/(float)y);
		
		
		System.out.println((float)x/y);
		
	}

}
