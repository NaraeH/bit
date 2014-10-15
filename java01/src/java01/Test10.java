/*
<2진수, 8진수, 10진수, 16진수 표현>
2진수: 표현불가!
8진수: 0으로 시작
10진수: 고대로
16진수: 0x or 0X로 시작
	*/
package java01;

public class Test10 {
	public static void main(String[] args) {
		int i1 = 13;
		int i2 = 016;
		int i3 = 0xd;
		int i4 = 0Xd;
		int i5 = 0xD;
		int i6 = 0XD;
	
		System.out.println(i1);
		System.out.println(i2);
		System.out.println(i3);
		System.out.println(i4);
		System.out.println(i5);
		System.out.println(i6);
	}
}
