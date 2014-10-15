/*<부동 소수점>
1. 상수의 표현
  30f or 30F <= 40byte 부동 소수점
  30.0 or 40.0 <= 8byte 부동 소수점
 * 부동 소수점? => 소수점이 움직인다.
 * 3.14 = 31.4 * 10^-1 = 314*10^-2= 0.314*10^1....
        = 31.4 * 10E-1 = 314*10E-2= 0.314*10E1....
        
2. 부동 소수점을 메모리에 저장하려면?
  */

package java01;

public class Test09 {

	public static void main(String[] args) {
		//부동 소수점 표기예
		//f는 해당 숫자가 float라는 뜻
		//E는 ^(몇승)을 의미
		float f1 = 3.14f;
		float f2 = 3.14F;
		float f3 = 31.4E-1f;
		float f4 = 314E-2f;
		float f5 = 0.314E1f;
		
		float test01 = Float.MIN_NORMAL;
		float test02 = Float.MAX_VALUE;
		
		System.out.println(test01);
		System.out.println(test02);
		
		//다음 값을 저장할 수는 있지만, 정수부 메모리 크기를 넘는 값은 0으로 취급한다.
		//그래서, 정확한 값이 지정되지 않는다.(소수점 아래는 짤린다)
		// 오류가 발생하지 않는다 하더라도 주의하라!
		float f6 = 34938948930284934083284984093f;
		
		//부동소수점 변수에 값을 저장할 때는
		//flaot x => 소수점을 뗀 6자리(7자리 일부)
		//double y => 소주점을 뗀 15자리(16자리 일부)
		
		//주의해야할 예
		float i = 3.14159f;
		float j = 15654.7f;
		
		float result = i + j; //*주의: 두 값을 더한 결과는 유효자릿수를 넘는다.
		
		System.out.println(result);
				
		

	}

}
