/*정수변수
byte(1): -128 ~ 127
short(2): -32768 ~ 32767
int(4) : 약 -21억 ~ 21억
long(8): 약 -922경 ~ 922경
*/
package java01;

public class Test08 {

	public static void main(String[] args) {
		
		byte minByte = Byte.MIN_VALUE;
		byte maxByte = Byte.MAX_VALUE;
		
		short minShort = Short.MIN_VALUE;
		short maxShort = Short.MAX_VALUE;
		
		int minInt = Integer.MIN_VALUE;
		int maxInt = Integer.MAX_VALUE;

		long minLong = Long.MIN_VALUE;
		long maxLong = Long.MAX_VALUE;
		
		//128이상되면 크기가 넘치므로 error! => int의 크기를 갖고있는 변수인데 byte로 선언하였으므로
		//error: type mismatch:cannot convert from int to byte 
		//minByte = -129;
		//maxByte = 128;
		maxByte = 127;
		
		//Literal: 정수 리터럴 => 4바이트 정수값, 8바이트 정수값
		//10, 20 <= 4바이트 저수
		//10L, 10l, 20L, 20l <= 8바이트 정수
		
		//상수 값이 int(4)이라도 메모리에 저장할 수 있으면 허락한다.
		byte b = 127; //허락!
		
		//상수 값을 메모리에 저장할 수 없으면 원래대로 오류를 발생한다.
		//byte b2 = 128; //error: cannot convert from int to bytes
		
		//byte b3 = 30L; //error: cannot convert from long to bytes => byte는 8바이트 상수값 저장 불가
		
		
	}

}
