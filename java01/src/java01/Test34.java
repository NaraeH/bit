/*
 <형변환>
 -정의: 다른 데이터 형의 임시 메모리를 만들고 값을 복사하는 것.
 -명시적 형변환: 명령어를 사용하여 형변환을 지정하는 것
   (임시메모리의 데이터형) 값;
   ex) int i = 10;
       byte b = (byte)i
 -암시적 형변환: JVM이 내부 규칙에 따라 자동으로 형변환 하는것
 * */
package java01;

public class Test34 {

	public static void main(String[] args) {
		int i = 10;
		
		//l-value: left-value, r-value: right value
		//l-value는 반드시 메모리여야 하고, r-value는 메모리 또는 상수 값이다.
		// 비록 상수 값이 4바이트 정수 이지만, 메모리(l-value)의 널 수 있으면 허락한다.
		byte b = 10;
		// 상수가 아닌 경우는 값을 l-value(memory)에 넣을 수 있다하더라도 허락하지 않는다.
		//byte b2 = i;  //오류
		
		//해결책: r-value을 강제로 l-value 변수에 넣는 방법
		//단, r-value에 l-value가 온전히 저장할 수 있다는 확신이 있어야한다.
		byte b2 = (byte)i;
		
		//그러나 다음의 경우를 조심해야 한다.
		int i2 = 128;
		byte b4 = (byte)i2;
		//JVM은 i2의 값에서 상위 3바이트를 버리고 마지막 바이트를 b4에 넣는다.
		//이것이 문제로다!
		//128: 0000 0000 0000 0000 0000 0001 0000 0000
		
		System.out.println(b4); //1000 0000: = -128

		int i3 = 356;
		byte b5 = (byte)i3;
		//JVM은 i2의 값에서 상위 3바이트를 버리고 마지막 바이트를 b4에 넣는다.
		//이것이 문제로다!
		//128: 0000 0000 0000 0000 0000 0001 0000 0000
		
		System.out.println(b5); //1000 0000: = -128
		

	}

}
